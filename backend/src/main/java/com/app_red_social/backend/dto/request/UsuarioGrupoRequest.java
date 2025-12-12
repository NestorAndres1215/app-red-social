package com.app_red_social.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioGrupoRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;
    private String descripcion;
    private String foto;
    private String privacidad;
    private String estado;
    private String creador;
}
