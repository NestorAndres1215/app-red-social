package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGrupo {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String foto;
    private String privacidad;
    private String estado;
    private String creador;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}
