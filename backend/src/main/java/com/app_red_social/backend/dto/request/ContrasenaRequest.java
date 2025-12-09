package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContrasenaRequest {

    @NotBlank(message = "EL USUARIO  ES OBLIGATORIO.")
    private String usuario;

    @NotBlank(message = "LA CONTRASENIA ES OBLIGATORIO.")
    private String nuevaContrasena;

    @NotBlank(message = "LA CONTRASENIA ES OBLIGATORIO.")
    private String confirmarContrasena;
}
