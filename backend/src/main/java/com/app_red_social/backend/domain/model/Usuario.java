package com.app_red_social.backend.domain.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private String codigo;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String banner;
    private String perfil;
    private String provider;
    private String genero;
    private String nacionalidad;
    private String ciudad;
    private String pais;
    private Integer seguidores;
    private Integer seguidos;
    private Integer publicaciones;
    private String presentacion;
    private String verificado;
    private Boolean cuentaPrivada;
    private Login login;
}
