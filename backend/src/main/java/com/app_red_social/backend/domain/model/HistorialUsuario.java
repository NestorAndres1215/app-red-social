package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialUsuario {

    private String codigo;
    private LocalDateTime fechaRegistro;
    private Login login;
    private String modulo;
    private String titulo;
    private String detalle;
    private String estado;
}
