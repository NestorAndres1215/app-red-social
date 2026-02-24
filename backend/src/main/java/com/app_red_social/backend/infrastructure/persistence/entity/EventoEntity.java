package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoEntity {

    @Id
    @Column(name = "ev_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ev_nombre", length = 200)
    private String nombre;

    @Column(name = "ev_detalle", length = 200)
    private String detalle;

    @Column(name = "ev_usuario_creador", length = 200)
    private String usuarioCreador;

    @Column(name = "ev_invitados_estado", length = 200)
    private String invitadoEstado;

    @Column(name = "ev_grupo", length = 200)
    private String grupo;

    @Column(name = "ev_banner", length = 200)
    private String banner;

    @Column(name = "ev_estado", length = 200)
    private String estado;

    @Column(name = "ev_modalidad", length = 200)
    private String modalidad;

    @Column(name = "ev_ubicacion", length = 200)
    private String ubicacion;

    @Column(name = "ev_fecha_inicio", length = 200)
    private LocalDateTime fechaInicio;

    @Column(name = "ev_fecha_finalizacion", length = 200)
    private LocalDateTime fechaFinalizacion;

    @Column(name = "ev_fecha_registro", length = 200)
    private LocalDateTime fechaRegistro;
}
