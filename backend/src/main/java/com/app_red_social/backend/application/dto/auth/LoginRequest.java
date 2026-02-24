package com.app_red_social.backend.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "EL USUARIO O CORREO ES OBLIGATORIO.")
    private String login;

    @NotBlank(message = "LA CONTRASEÑA ES OBLIGATORIA.")
    private String password;

}
