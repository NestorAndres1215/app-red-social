package com.app_red_social.backend.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suspensiones {

    private String codigo;
    private LocalDateTime fecha_suspension;
    private LocalDateTime fecha_expiracion;
    private String motivo_corto;
    private String detalle_motivo;
    private Usuario usuario;
}
