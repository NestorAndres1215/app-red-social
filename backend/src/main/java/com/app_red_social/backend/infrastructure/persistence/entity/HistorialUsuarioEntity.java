package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialUsuarioEntity {

    @Id
    @Column(name = "hu_codigo")
    private String codigo;

    @Column(name = "hu_fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "hu_login", referencedColumnName = "lg_codigo")
    private LoginEntity login;

    @Column(name = "hu_modulo", length = 100)
    private String modulo;

    @Column(name = "hu_titulo", columnDefinition = "TEXT")
    private String titulo;

    @Column(name = "hu_detalle", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "hu_estado", nullable = false)
    private String estado;
}
