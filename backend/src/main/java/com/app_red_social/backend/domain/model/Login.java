package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String codigo;
    private String username;
    private String email;
    private String telefono;
    private String password;
    private LocalDateTime ultimoLogin;
    private LocalDateTime fechaCreacion;
    private EstadoUsuario estadoUsuario;
    private Rol rol;
}
