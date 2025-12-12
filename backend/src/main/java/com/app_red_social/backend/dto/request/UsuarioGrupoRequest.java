package com.app_red_social.backend.dto.request;

import lombok.Data;

@Data
public class UsuarioGrupoRequest {
    private String nombre;
    private String descripcion;
    private String foto;
    private String privacidad;   // PUBLICO, PRIVADO
    private String estado;       // ACTIVO, INACTIVO
    private String creador;
}
