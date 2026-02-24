package com.app_red_social.backend.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevisionSuspension {

    private String codigo;
    private LocalDateTime fecha_revision;
    private String estado_revision;
    private String observacion;
    private Suspensiones suspensiones;
    private Moderador moderador;
}
