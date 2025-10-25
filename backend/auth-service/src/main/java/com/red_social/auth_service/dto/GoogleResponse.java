package com.red_social.auth_service.dto;

import lombok.Data;

@Data
public class GoogleResponse {
    private String access_token;
    private String expires_in;
    private String scope;
    private String token_type;
    private String id_token;

}
