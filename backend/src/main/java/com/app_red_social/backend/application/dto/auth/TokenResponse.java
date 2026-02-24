package com.app_red_social.backend.application.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class TokenResponse {
    private String token;
}