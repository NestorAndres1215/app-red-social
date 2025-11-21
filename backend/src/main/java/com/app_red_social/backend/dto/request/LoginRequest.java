package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class LoginRequest {

    @NotBlank(message = "EL USUARIO O CORREO ES OBLIGATORIO.")
    private String login;

    @NotBlank(message = "LA CONTRASEÑA ES OBLIGATORIA.")
    private String password;

}

