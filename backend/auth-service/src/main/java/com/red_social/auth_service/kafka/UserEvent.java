package com.red_social.auth_service.kafka;

import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEvent {
    private String eventType; // Ejemplo: USER_REGISTERED, USER_UPDATED
    private String userId;
    private String username;
    private String nombre;
    private String apellido;
    private String genero;
    private String pais;
    private String rol;
    private Integer edad;
    private String verificado;
    private String proveedor;
    private String estado; // ACTIVO, INACTIVO, BLOQUEADO
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
}
