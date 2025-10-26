package com.red_social.analysis_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_verificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasVerificacion {

    @Id
    @Column(name = "ev_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ev_estado", length = 45, nullable = false)
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Column(name = "ev_porcentaje", nullable = false)
    @DecimalMin(value = "0.0", message = "El porcentaje no puede ser menor a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede superar 100")
    private Double porcentaje;

    @Column(name = "ev_total_usuarios", nullable = false)
    @Min(value = 0, message = "El total de usuarios no puede ser negativo")
    private Integer totalUsuarios;

    @Column(name = "ev_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;

}
