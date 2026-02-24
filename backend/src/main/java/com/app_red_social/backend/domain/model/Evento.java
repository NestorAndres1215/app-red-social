package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    private String codigo;
    private String nombre;
    private String detalle;
    private String usuarioCreador;
    private String invitadoEstado;
    private String grupo;
    private String banner;
    private String estado;
    private String modalidad;
    private String ubicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaRegistro;
}
