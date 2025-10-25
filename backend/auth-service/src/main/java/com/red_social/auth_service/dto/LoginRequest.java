package com.red_social.auth_service.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;

}
