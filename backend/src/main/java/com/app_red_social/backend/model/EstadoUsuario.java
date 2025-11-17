package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "estado_usuario")
public class EstadoUsuario {

    @Id
    @Column(name = "st_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "st_nombre", length = 250, nullable = false, unique = true)
    private String nombre;

    @Column(name = "st_descripcion", length = 250, nullable = false, unique = true)
    private String descripcion;
}
