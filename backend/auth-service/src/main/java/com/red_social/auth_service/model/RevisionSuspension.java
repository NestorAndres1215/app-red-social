package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

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
    @Column(name = "rs_codigo",length = 8, nullable = false, unique = true)
    private String codigo;


    @Column(name = "rs_fechaRevision", nullable = false)
    @NotNull(message = "La fecha de revisión es obligatoria")
    private LocalDate fechaRevision;

    @Column(name = "rs_estadoRevision", nullable = false)
    @NotBlank(message = "El estado de la revisión es obligatorio")
    private String estadoRevision;

    @Column(name = "rs_observacion", length = 500)
    @Size(max = 500, message = "La observación no puede superar los 500 caracteres")
    private String observacion;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_suspension", referencedColumnName = "sus_codigo")
    private Suspensiones suspensiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_moderador", referencedColumnName = "mod_codigo")
    private Moderador moderador;

}
