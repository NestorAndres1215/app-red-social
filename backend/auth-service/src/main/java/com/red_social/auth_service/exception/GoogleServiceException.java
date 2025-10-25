package com.red_social.auth_service.exception;

/**
 * Excepción personalizada para errores en la integración con servicios de Google.
 */
public class GoogleServiceException extends RuntimeException {
    public GoogleServiceException(String message) {
        super(message);
    }
}
