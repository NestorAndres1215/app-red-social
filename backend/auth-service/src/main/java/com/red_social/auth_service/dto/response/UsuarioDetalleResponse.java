package com.red_social.auth_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsuarioDetalleResponse {
    private String codigo;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String presentacion;
    private String photoUrl;
    private byte[] banner;
    private byte[] perfil;
    private String correo;
    private String telefono;
    private String username;
    private String rolNombre;
    private LocalDateTime ultimoLogin;
    private String estadoNombre;

}
