package com.app_red_social.backend.domain.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGrupoMiembro {

    private String codigo;
    private String estadoMiembro;
    private LocalDateTime fechaRegistro;
    private UsuarioGrupo usuarioGrupo;
    private Login login;

}
