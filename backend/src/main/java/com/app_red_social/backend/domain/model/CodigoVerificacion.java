package com.app_red_social.backend.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoVerificacion {

    private String codigo;
    private String correo;
    private String usuario;
    private String tipoVerificacion;
    private String codigoVerificacion;
    private LocalDateTime fechaGeneracion;


}
