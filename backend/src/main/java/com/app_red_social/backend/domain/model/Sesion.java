package com.app_red_social.backend.domain.model;


import com.app_red_social.backend.infrastructure.persistence.entity.LoginEntity;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {

    private String codigo;
    private LocalDateTime inicioSesion;
    private LocalDateTime finSesion;
    private String ubicacion;
    private String navegador;
    private LoginEntity login;

}
