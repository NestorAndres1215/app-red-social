package com.red_social.analysis_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_genero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasGenero {

    @Id
    @Column(name = "eg_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @Column(name = "eg_genero", length = 255, nullable = false)
    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    @Column(name = "eg_porcentaje", nullable = false)
    @NotNull(message = "El porcentaje es obligatorio")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    private Double porcentaje;

    @Column(name = "eg_total_usuarios", nullable = false)
    @Min(value = 0, message = "El total de usuarios no puede ser negativo")
    private Integer totalUsuarios;

    @Column(name = "eg_nuevo_semana", length = 100, nullable = false)
    @Min(value = 0, message = "Los nuevos por semana no pueden ser negativos")
    private Integer nuevosSemana;

    @Column(name = "eg_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;

}
