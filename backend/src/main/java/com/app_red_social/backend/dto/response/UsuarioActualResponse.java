package com.app_red_social.backend.dto.response;

import lombok.Builder;
import lombok.Data;

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
    private byte[] banner;
    private byte[] perfil;
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
