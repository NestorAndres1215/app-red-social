package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(name = "sus_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "sus_fechaSuspension")
    private LocalDate fecha_suspension;

    @Column(name = "sus_fechaExpiracion")
    private LocalDate fechaExpiracion;


    @Column(name = "sus_horaSuspension")
    private LocalTime horaSuspension;

    @Column(name = "sus_motivoCorto", length = 700, nullable = false)
    @NotBlank(message = "El motivo corto no puede estar vacío")
    @Size(max = 700, message = "El motivo corto no puede superar los 700 caracteres")
    private String detalleMotivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sus_usuario", referencedColumnName = "lg_codigo")
    private Login login;

}
