package com.app_red_social.backend.dto.response;

import lombok.*;

@Data
@Builder
public class UsuarioListaResponse {

    // Datos del administrador
    private String codigo;
    private String nombre;
    private String apellido;
    private String edad;
    private String fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String perfil;

    // Datos de login
    private String username;
    private String correo;
    private String telefono;
    private String fechaRegistro;
    private String ultimoLogin;

    // Estado del usuario
    private String estado;
}
