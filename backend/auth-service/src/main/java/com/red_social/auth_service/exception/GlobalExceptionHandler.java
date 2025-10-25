package com.red_social.auth_service.exception;

import com.red_social.auth_service.constants.ErrorGlobalConstants;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Construye una respuesta estandarizada para cualquier excepción.
     */
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }

    /**
     * 🔹 JWT inválido o expirado
     */
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleJwtException(JwtAuthenticationException ex) {
        logger.warn("🔒 Error JWT: {}", ex.getMessage());
        return buildResponse(HttpStatus.UNAUTHORIZED, ErrorGlobalConstants.NO_AUTORIZADO, ex.getMessage());
    }

    /**
     * 🔹 Recurso duplicado
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleResourceExists(ResourceAlreadyExistsException ex) {
        logger.warn("⚠️ Recurso duplicado: {}", ex.getMessage());
        return buildResponse(HttpStatus.CONFLICT, ErrorGlobalConstants.CONFLICTO, ex.getMessage());
    }

    /**
     * 🔹 Recurso no encontrado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        logger.warn("🔎 Recurso no encontrado: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ErrorGlobalConstants.NO_ENCONTRADO, ex.getMessage());
    }

    /**
     * 🔹 Acceso denegado
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
        logger.warn("🚫 Acceso denegado: {}", ex.getMessage());
        return buildResponse(HttpStatus.FORBIDDEN, ErrorGlobalConstants.PROHIBIDO, ex.getMessage());
    }

    /**
     * 🔹 Solicitud inválida
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex) {
        logger.warn("❗ Solicitud inválida: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, ErrorGlobalConstants.SOLICITUD_INVALIDA, ex.getMessage());
    }

    /**
     * 🔹 Error en integración con Google
     */
    @ExceptionHandler(GoogleServiceException.class)
    public ResponseEntity<Map<String, Object>> handleGoogleService(GoogleServiceException ex) {
        logger.error("🌐 Error en comunicación con Google: {}", ex.getMessage());
        return buildResponse(HttpStatus.BAD_GATEWAY, ErrorGlobalConstants.ERROR_API_GOOGLE, ex.getMessage());
    }

    /**
     * 🔹 Error de validación
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(ValidationException ex) {
        logger.warn("📋 Error de validación: {}", ex.getMessage());
        return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ErrorGlobalConstants.ERROR_VALIDACION, ex.getMessage());
    }

    /**
     * 🔹 Error de tiempo de ejecución
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        logger.error("💥 Error de ejecución: ", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorGlobalConstants.ERROR_INTERNO, ex.getMessage());
    }

    /**
     * 🔹 Excepción genérica final
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        logger.error("⚠️ Error no controlado: ", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorGlobalConstants.ERROR_PROCESO, ex.getMessage());
    }
}
