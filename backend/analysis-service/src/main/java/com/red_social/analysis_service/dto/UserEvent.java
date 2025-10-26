package com.red_social.analysis_service.dto;

import lombok.Data;

@Data
public class UserEvent {
    private String username;
    private String nombre;
    private String apellido;
    private String genero;
    private String pais;
    private Integer edad;
    private String rol;
    private String estado;
    private String verificado;
    private String proveedor;
}
