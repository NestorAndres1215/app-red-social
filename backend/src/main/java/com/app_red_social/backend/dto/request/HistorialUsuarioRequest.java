package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class HistorialUsuarioRequest {

    private String codigo; // opcional, backend puede ignorarlo

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
