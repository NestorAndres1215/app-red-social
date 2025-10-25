package com.red_social.auth_service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String presentacion;
    private String codigoUsuario;
    private String username;
    private String email;
    private String telefono;
    private String password;

}
