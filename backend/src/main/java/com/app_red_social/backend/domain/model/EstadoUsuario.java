package com.app_red_social.backend.domain.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuario {

    private String codigo;
    private String nombre;
    private String descripcion;
}
