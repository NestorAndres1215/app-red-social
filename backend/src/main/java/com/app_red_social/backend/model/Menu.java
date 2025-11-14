package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "mn_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "mn_nivel", length = 8, nullable = false)
    private String nivel;

    @Column(name = "mn_nombre", length = 250, nullable = false)
    private String nombre;

    @Column(name = "mn_nombre", length = 250, nullable = false)
    private String tipo;

    @Column(name = "mn_nombre", length = 250, nullable = false)
    private String icono;

    @Column(name = "mn_nombre", length = 250, nullable = false)
    private String categoria;

    @Column(name = "mn_nombre", length = 250, nullable = false)
    private String menuRuta;

    @ManyToOne
    @JoinColumn(name = "mu_rol", referencedColumnName = "rl_codigo")
    private Rol rol;
}
