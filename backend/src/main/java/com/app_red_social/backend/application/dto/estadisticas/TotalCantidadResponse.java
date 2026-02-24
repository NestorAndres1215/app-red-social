package com.app_red_social.backend.application.dto.estadisticas;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalCantidadResponse {
    private String total;
}
