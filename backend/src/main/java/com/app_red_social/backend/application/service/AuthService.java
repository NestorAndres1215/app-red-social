package com.app_red_social.backend.application.service;



import com.app_red_social.backend.application.dto.auth.LoginRequest;
import com.app_red_social.backend.application.dto.auth.TokenResponse;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.AuthUseCase;
import com.app_red_social.backend.domain.port.usecases.TokenUseCase;
import com.app_red_social.backend.infrastructure.security.JwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final LoginRepositoryPort loginRepository;
    private  final TokenUseCase tokenUseCase;
    private final JwtAdapter jwtUtil;

    @Override
    public Login actualUsuario(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken oauth) {

            String email = oauth.getPrincipal().getAttribute("email");

            return loginRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
        }

        String username = authentication.getName();

        return loginRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    @Override
    public Login authenticate(LoginRequest request) {
        Login login = loginRepository.findByUsername(request.getLogin())
                .orElseGet(() -> loginRepository.findByEmail(request.getLogin())
                        .orElseGet(() -> loginRepository.findByTelefono(request.getLogin())
                                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO))
                        )
                );
        return login;
    }

    @Override
    public String generateToken(Login login) {
        return jwtUtil.generateToken(login);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        Login login = authenticate(request);
        String token = generateToken(login);
//        tokenUseCase.registrar(token);
        return TokenResponse.builder().token(token).build();
    }
}