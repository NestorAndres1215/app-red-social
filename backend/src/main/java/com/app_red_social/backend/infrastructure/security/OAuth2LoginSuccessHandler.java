package com.app_red_social.backend.infrastructure.security;

import com.app_red_social.backend.application.dto.usuario.UsuarioRequest;
import com.app_red_social.backend.domain.constant.Auth;
import com.app_red_social.backend.domain.constant.Roles;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.domain.port.usecases.TokenUseCase;
import com.app_red_social.backend.domain.port.usecases.UsuarioUseCase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtAdapter adapter;
    private final UsuarioUseCase usuarioUseCase;
    private final TokenUseCase tokenUseCase;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();


        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("picture");
        String givenName = oAuth2User.getAttribute("given_name");
        String familyName = oAuth2User.getAttribute("family_name");

        UsuarioRequest usuarioRequest = UsuarioRequest.builder()
                .email(email)
                .nombre(givenName)
                .perfil(picture)
                .apellido(familyName)
                .provider(Auth.GOGGLE)
                .rol(Roles.ROLE_USER)
                .build();
        Usuario user = usuarioUseCase.saveOrUpdateGoogleUser(usuarioRequest);
        String token = adapter.generateToken(user.getLogin());
//        tokenUseCase.registrar(token);
        try {
            String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
            String redirectUrl = "http://localhost:4200/oauth2/redirect?token=" + encodedToken;
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
