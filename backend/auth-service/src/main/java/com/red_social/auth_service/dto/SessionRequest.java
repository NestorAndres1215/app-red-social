package com.red_social.auth_service.dto;

import lombok.Data;

@Data
public class SessionRequest {

    private String username;
    private String navegador;
    private String ubicacion;
}
