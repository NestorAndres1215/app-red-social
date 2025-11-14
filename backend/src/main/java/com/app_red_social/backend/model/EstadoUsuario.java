package com.app_red_social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
