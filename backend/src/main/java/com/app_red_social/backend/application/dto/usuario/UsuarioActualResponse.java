package com.app_red_social.backend.application.dto.usuario;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioActualResponse {
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String presentacion;
    private String photoUrl;
    private String banner;
    private String perfil;
    private String correo;
    private String telefono;
    private String username;
    private String rol;
    private String ultimoLogin;
    private String fechaCreacion;
    private String codigoLogin;
    private String estadoNombre;
    private String estadoDescripcion;
}