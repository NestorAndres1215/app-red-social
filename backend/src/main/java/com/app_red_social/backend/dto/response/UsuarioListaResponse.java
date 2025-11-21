package com.app_red_social.backend.dto.response;

import lombok.*;

@Data
@Builder
public class UsuarioListaResponse {

    // Campos comunes a todos
    private String codigo;
    private String nombre;
    private String apellido;
    private String edad;
    private String fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String perfil;

    // Campos específicos solo para Usuario
    private String ciudad;
    private String pais;
    private String banner;
    private String foto;
    private String presentacion;
    private String cuentaPrivada;
    private String publicaciones;
    private String seguidores;
    private String seguidos;
    private String verificado;

    // Campos de Login (todos)
    private String username;
    private String correo;
    private String telefono;
    private String rol;
    private String fechaRegistro;
    private String ultimoLogin;

    // Estado
    private String estado;
}
