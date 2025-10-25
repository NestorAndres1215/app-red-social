package com.red_social.auth_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Se lanza cuando los datos enviados por el cliente son inválidos o incompletos.
 * Retorna 400 BAD_REQUEST.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}