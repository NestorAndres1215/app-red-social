package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "suspensiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Suspensiones {

    @Id
    @Column(name = "sus_codigo")
    private String codigo;

    @Column(name = "sus_fecha_suspension")
    private LocalDateTime fecha_suspension;

    @Column(name = "sus_fecha_expiracion")
    private LocalDateTime fecha_expiracion;

    @Column(name = "sus_motivo_corto")
    private String motivo_corto;


    @Column(name = "sus_detalle_motivo")
    private String detalle_motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sus_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;
}
