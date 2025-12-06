package com.app_red_social.backend.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RevisionSuspencionRequest {
    private String codigo;
    private String detalle;
    private String correo;
    private String asunto;
    private String motivo;
    private String usuario;
    private String moderador;
    private LocalDate fechaSuspension;
}
