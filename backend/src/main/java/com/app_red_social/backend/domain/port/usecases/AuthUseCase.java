package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.application.dto.auth.LoginRequest;
import com.app_red_social.backend.application.dto.auth.TokenResponse;
import com.app_red_social.backend.domain.model.Login;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public interface AuthUseCase {

    Login actualUsuario(Authentication authentication);

    Login authenticate(LoginRequest request);

    String generateToken(Login login);

    TokenResponse login(LoginRequest request);

}