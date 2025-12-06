package com.app_red_social.backend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RevisionResponse {

    private String codigoRevision;

    private LocalDateTime fechaRevision;

    private String estadoRevision;

    private String observacionRevision;

    private String codigoSuspension;

    private LocalDateTime fechaSuspension;

    private LocalDateTime fechaExpiracion;

    private String motivoSuspension;

    private String detalleSuspension;

    private String codigoUsuario;

    private String usernameUsuario;

    private String correoUsuario;

    private String codigoModerador;

    private String nombreModerador;

    private String apellidoModerador;

    private String correoModerador;

}
