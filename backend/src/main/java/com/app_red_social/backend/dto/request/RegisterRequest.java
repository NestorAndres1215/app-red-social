package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class RegisterRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @NotNull(message = "La edad es obligatoria.")
    @Min(value = 18, message = "Debe tener al menos 18 años.")
    private Integer edad;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El género es obligatorio.")
    private String genero;

    @NotBlank(message = "La nacionalidad es obligatoria.")
    private String nacionalidad;

    @NotBlank(message = "El username es obligatorio.")
    private String username;

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "El correo no tiene un formato válido.")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos numéricos.")
    private String telefono;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

}
