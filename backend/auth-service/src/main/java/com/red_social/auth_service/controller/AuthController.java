package com.red_social.auth_service.controller;

import com.red_social.auth_service.constants.AuthConstants;
import com.red_social.auth_service.dto.request.LoginRequest;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) throws Exception {
        String jwt = authHeader.replace("Bearer ", "");
        tokenService.invalidarToken(jwt);
        return ResponseEntity.ok(AuthConstants.CERRAR_SESION);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody LoginRequest jwtRequest) throws Exception {
        System.out.println("ESTO ES ES TOKEN");
        System.out.println(jwtRequest.toString());
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<Login> obtenerUsuarioActual(Principal principal) {
        return ResponseEntity.ok(authService.actualUsuario(principal));
    }


}
