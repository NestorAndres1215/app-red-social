package com.app_red_social.backend.dto.request;

import lombok.Data;

@Data
public class SessionRequest {

    private String username;
    private String navegador;
    private String ubicacion;
}