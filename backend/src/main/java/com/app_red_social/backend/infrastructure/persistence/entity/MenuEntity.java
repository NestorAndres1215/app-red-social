package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menu")
public class MenuEntity {

    @Id
    @Column(name = "mn_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "mn_nivel", length = 8)
    private Integer nivel;

    @Column(name = "mn_nombre", length = 250)
    private String nombre;

    @Column(name = "mn_tipo", length = 250)
    private String tipo;

    @Column(name = "mn_icono", length = 250)
    private String icono;

    @Column(name = "mn_categoria", length = 250)
    private String categoria;

    @Column(name = "mn_ruta", length = 250)
    private String menuRuta;

    @ManyToOne
    @JoinColumn(name = "mu_rol", referencedColumnName = "rl_codigo")
    private RolEntity rol;

}
