package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class SessionRequest {

    @NotBlank(message = "El username es obligatorio.")
    private String username;

    @NotBlank(message = "El navegador es obligatorio.")
    private String navegador;

    @NotBlank(message = "La ubicación es obligatoria.")
    private String ubicacion;
}