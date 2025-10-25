package com.red_social.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Se lanza cuando el token JWT es inválido, expirado o ausente.
 * Retorna 401 UNAUTHORIZED.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}