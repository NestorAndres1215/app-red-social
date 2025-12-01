package com.app_red_social.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AdministradorActualResponse {

    private String codigoAdministrador;
    private String nombre;
    private String apellido;
    private String nombreCompleto;

    private Integer edad;
    private String fechaNacimiento;
    private String fechaNacimientoFormateada;
    private Integer edadCalculada;

    private String genero;
    private String nacionalidad;
    private byte[] fotoPerfil;

    private String codigoLogin;
    private String correo;
    private String telefono;
    private String username;
    private String rol;
    private String estadoUsuario;
    private String ultimoLogin;
    private String fechaRegistro;
}
