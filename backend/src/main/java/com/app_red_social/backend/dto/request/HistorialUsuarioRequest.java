package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistorialUsuarioRequest {

    private String codigo;

    @NotNull(message = "La fecha del historial es obligatoria.")
    @PastOrPresent(message = "La fecha del historial no puede ser futura.")
    private LocalDate fecha;

    @NotBlank(message = "El usuario del historial es obligatorio.")
    private String usuario;

    @NotBlank(message = "El estado del historial es obligatorio.")
    private String estado;

    @NotBlank(message = "El detalle del historial es obligatorio.")
    @Size(max = 500, message = "El detalle no puede exceder los 500 caracteres.")
    private String detalle;

}
