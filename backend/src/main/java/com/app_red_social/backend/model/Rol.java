package com.app_red_social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "rol")
@Getter
@Entity
@Setter
@Builder
public class Rol {

    @Id
    @Column(name = "rl_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "rl_nombre", length = 250, nullable = false, unique = true)
    private String nombre;

    @Column(name = "rl_descripcion", length = 250, nullable = false, unique = true)
    private String descripcion;


}
