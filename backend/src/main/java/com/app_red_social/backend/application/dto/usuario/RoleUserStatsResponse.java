package com.app_red_social.backend.application.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserStatsResponse {
    private int cantidad;
    private double porcentaje;
    private String periodo;
}
