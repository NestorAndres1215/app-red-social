package com.app_red_social.backend.application.dto.historial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistorialResponse {

    private String codigoHistorial;

    private String fechaHistorial;

    private String horaHistorial;

    private String usuarioHistorial;

    private String estadoHistorial;

    private String tituloHistorial;

    private String moduloHistorial;

    private String detalleHistorial;
}
