package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "revisiones_suspension")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RevisionSuspension {

    @Id
    @Column(name = "rs_codigo")
    private String codigo;

    @Column(name = "rs_fecha_revision")
    private LocalDateTime fecha_revision;

    @Column(name = "rs_estado_revision")
    private String estado_revision;

    @Column(name = "rs_observacion")
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_suspension", referencedColumnName = "sus_codigo")
    private Suspensiones suspensiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_moderador", referencedColumnName = "mod_codigo")
    private Moderador moderador;
}
