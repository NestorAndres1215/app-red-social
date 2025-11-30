package com.app_red_social.backend.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistorialResponse {

    private String codigoHistorial;

    private String fechaHistorial;   // Puedes usar LocalDate si deseas

    private String horaHistorial;    // Puedes usar LocalTime si deseas

    private String usuarioHistorial;

    private String estadoHistorial;

    private String tituloHistorial;  // Nuevo

    private String moduloHistorial;  // Nuevo

    private String detalleHistorial;
}
