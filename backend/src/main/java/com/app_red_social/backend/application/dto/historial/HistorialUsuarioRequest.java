package com.app_red_social.backend.application.dto.historial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HistorialUsuarioRequest {

    @NotBlank(message = "EL USUARIO DEL HISTORIAL ES OBLIGATORIO.")
    private String usuario;

    @NotBlank(message = "EL ESTADO DEL HISTORIAL ES OBLIGATORIO.")
    private String estado;

    @NotBlank(message = "EL TÍTULO DEL HISTORIAL ES OBLIGATORIO.")
    private String titulo;

    @NotBlank(message = "EL MÓDULO DEL HISTORIAL ES OBLIGATORIO.")
    private String modulo;

    @NotBlank(message = "EL DETALLE DEL HISTORIAL ES OBLIGATORIO.")
    @Size(max = 500, message = "EL DETALLE NO PUEDE EXCEDER LOS 500 CARACTERES.")
    private String detalle;

}
