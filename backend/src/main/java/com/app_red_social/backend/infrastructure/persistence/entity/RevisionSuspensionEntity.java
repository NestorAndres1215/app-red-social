package com.app_red_social.backend.infrastructure.persistence.entity;

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
public class RevisionSuspensionEntity {

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
    private SuspensionesEntity suspensiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_moderador", referencedColumnName = "mod_codigo")
    private ModeradorEntity moderador;
}
