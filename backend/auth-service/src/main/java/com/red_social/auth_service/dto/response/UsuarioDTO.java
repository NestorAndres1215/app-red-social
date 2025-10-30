package com.red_social.auth_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
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

    // getters y setters
}
