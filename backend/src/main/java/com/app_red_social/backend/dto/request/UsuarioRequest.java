package com.app_red_social.backend.dto.request;

import lombok.*;

@Data
@Builder
public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String photoUrl;
    private String provider;
    private String rol;

}
