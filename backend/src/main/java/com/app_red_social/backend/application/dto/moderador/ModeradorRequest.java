package com.app_red_social.backend.application.dto.moderador;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ModeradorRequest {

    @NotBlank(message = "EL NOMBRE ES OBLIGATORIO.")
    private String nombre;

    @NotBlank(message = "EL APELLIDO ES OBLIGATORIO.")
    private String apellido;

    private Integer edad;

    @NotNull(message = "LA FECHA DE NACIMIENTO ES OBLIGATORIA.")
    @Past(message = "LA FECHA DE NACIMIENTO DEBE SER UNA FECHA PASADA.")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "EL GÉNERO ES OBLIGATORIO.")
    private String genero;

    @NotBlank(message = "LA NACIONALIDAD ES OBLIGATORIA.")
    private String nacionalidad;

    @NotBlank(message = "EL USERNAME ES OBLIGATORIO.")
    private String username;

    @NotBlank(message = "EL EMAIL ES OBLIGATORIO.")
    @Email(message = "EL CORREO NO TIENE UN FORMATO VÁLIDO.")
    private String email;

    @NotBlank(message = "EL TELÉFONO ES OBLIGATORIO.")
    @Pattern(regexp = "^[0-9]{9}$", message = "EL TELÉFONO DEBE TENER 9 DÍGITOS NUMÉRICOS.")
    private String telefono;

    @NotBlank(message = "LA CONTRASEÑA ES OBLIGATORIA.")
    @Size(min = 6, message = "LA CONTRASEÑA DEBE TENER AL MENOS 6 CARACTERES.")
    private String password;
}
