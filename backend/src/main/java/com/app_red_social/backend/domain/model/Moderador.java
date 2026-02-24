package com.app_red_social.backend.domain.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moderador {
    private String codigo;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String perfil;
    private String genero;
    private Login login;


}
