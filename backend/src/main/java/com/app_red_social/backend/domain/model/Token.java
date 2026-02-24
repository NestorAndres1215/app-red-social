package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String codigo;
    private String token;
    private String valido;
    private LocalDateTime fechaExpiracion;
    private LocalDateTime fechaRegistro;
    private Login login;

}
