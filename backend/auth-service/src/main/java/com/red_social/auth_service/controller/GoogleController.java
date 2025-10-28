package com.red_social.auth_service.controller;

import com.red_social.auth_service.dto.response.GoogleResponse;
import com.red_social.auth_service.model.Usuario;
import com.red_social.auth_service.security.JwtUtil;
import com.red_social.auth_service.service.GoogleService;
import com.red_social.auth_service.service.TokenService;
import com.red_social.auth_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/google")
@Tag(name = "Authenticacion con Google")
public class GoogleController {

    private final GoogleService googleService;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @GetMapping("/login-url")
    public Map<String, String> getLoginUrl() {
        String scope = "openid email profile";
        String responseType = "code";

        String url = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=" + responseType
                + "&scope=" + scope;

        return Map.of("url", url);
    }

    @PostMapping("/loginWithGoogle")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> body) {
        try {
            String code = body.get("code");


            // 1️⃣ Intercambiar code por token en Google
            GoogleResponse tokenResponse = googleService.exchangeCodeForToken(code);
            // 2️⃣ Obtener información del usuario
            Map<String, Object> userInfo = googleService.getUserInfo(tokenResponse.getAccess_token());
            String name = (String) userInfo.get("name");


            // 3️⃣ Registrar/actualizar en MySQL
            Usuario user = usuarioService.loginRegisterGoogle(userInfo);
            // 4️⃣ Generar JWT propio
            String jwt = jwtUtil.generateToken(user.getLogin());
            // 5️⃣ Guardar token en base de datos
            tokenService.createToken(jwt);

            System.out.println(jwt);
            return ResponseEntity.ok(Map.of(
                    "token", jwt,
                    "email", user.getLogin().getEmail(),
                    "name", user.getNombre(),
                    "picture", user.getPhotoUrl()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
