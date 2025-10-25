package com.red_social.auth_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "codigo_verificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CodigoVerificacion {

    @Id
    @Column(name = "cv_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "cv_correo", length = 100, nullable = false)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 100, message = "El correo electrónico no puede superar los 100 caracteres")
    private String correo;

    @Column(name = "cv_usuario", length = 255, nullable = false)
    @NotBlank(message = "El código de usuario es obligatorio")
    private String usuario;

    @Column(name = "cv_codigoVerificacion", length = 10, nullable = false)
    @NotBlank(message = "El código de verificación es obligatorio")
    private String codigoVerificacion;

    @Column(name = "cv_fechaGeneracion")
    private LocalDate fechaGeneracion;

    @Column(name = "cv_horaGeneracion")
    private LocalTime horaGeneracion;
}
