package com.app_red_social.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String error;       // 🔹 Mensaje de error general
    private Object message;     // 🔹 Puede ser String o Map<String, String>
    private LocalDateTime timestamp;
    private int status;
}