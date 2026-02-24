package com.app_red_social.backend.domain.port.repository;


import com.app_red_social.backend.domain.model.Login;

public interface TokenProviderPort {
    String generateToken(Login login);

    String extractUsername(String token);

    boolean validateToken(String token, String username);
}
