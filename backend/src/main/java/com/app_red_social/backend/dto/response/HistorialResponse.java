package com.app_red_social.backend.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistorialResponse {
    private String codigoHistorial;
    private String fechaHistorial;  // si quieres LocalDate lo cambiamos
    private String horaHistorial;   // si quieres LocalTime lo cambiamos
    private String usuarioHistorial;
    private String estadoHistorial;
    private String detalleHistorial;
}
