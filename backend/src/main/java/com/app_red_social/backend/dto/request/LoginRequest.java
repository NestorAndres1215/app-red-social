package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class LoginRequest {

    @NotBlank(message = "El usuario o correo es obligatorio.")
    private String login;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

}
