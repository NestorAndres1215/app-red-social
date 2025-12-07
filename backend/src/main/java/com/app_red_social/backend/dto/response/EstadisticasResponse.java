package com.app_red_social.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstadisticasResponse {
    private String atributo;
    private String total;
    private String porcentaje;
}
