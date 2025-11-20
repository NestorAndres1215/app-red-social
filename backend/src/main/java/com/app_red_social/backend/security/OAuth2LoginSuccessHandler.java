package com.app_red_social.backend.security;

import com.app_red_social.backend.constants.Auth;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.dto.request.UsuarioRequest;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.service.LoginService;
import com.app_red_social.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtTokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("picture");
        String givenName = oAuth2User.getAttribute("given_name");
        String familyName = oAuth2User.getAttribute("family_name");
System.out.println("EMAIL"+ email);
        System.out.println("PICTURE"+ picture);
        UsuarioRequest usuarioRequest = UsuarioRequest.builder()
                .email(email)
                .nombre(givenName)
                .photoUrl(picture)
                .apellido(familyName)
                .provider(Auth.GOGGLE)
                .rol(Roles.ROLE_USER)
                .build();
        System.out.println(usuarioRequest);
        Usuario user = usuarioService.saveOrUpdateGoogleUser(usuarioRequest);
        String token = jwtTokenProvider.generateToken(user.getLogin());

        try {
            String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
            String redirectUrl = "http://localhost:4200/oauth2/redirect?token=" + encodedToken;
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
