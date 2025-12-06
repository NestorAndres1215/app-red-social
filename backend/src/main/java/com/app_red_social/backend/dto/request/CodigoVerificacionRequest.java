package com.app_red_social.backend.dto.request;

import lombok.Data;

@Data
public class CodigoVerificacionRequest {
    private String correo;
    private String codigo;
}
