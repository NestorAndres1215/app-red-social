package com.app_red_social.backend.domain.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private String codigo;
    private Integer nivel;
    private String nombre;
    private String tipo;
    private String icono;
    private String categoria;
    private String menuRuta;
    private Rol rol;
}
