package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HistorialUsuario {

    @Id
    @Column(name = "hu_codigo")
    private String codigo;

    @Column(name = "hu_fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "hu_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    @Column(name = "hu_detalle", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "hu_estado", nullable = false)
    private String estado;

}
