package com.red_social.analysis_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_edad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasEdad {

    @Id
    @Column(name = "ee_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @Column(name = "ee_rango_edad", length = 255, nullable = false)
    @NotBlank(message = "El rango de edad no puede estar vacío")
    private String rangoEdad; // Ej: "18-25", "26-35", etc.

    @Column(name = "ee_porcentaje", nullable = false)
    @DecimalMin(value = "0.0", message = "El porcentaje no puede ser menor a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    private Double porcentaje;

    @Column(name = "ee_nuevo_semana", nullable = false)
    @Min(value = 0, message = "El número de nuevos usuarios por semana no puede ser negativo")
    private Integer nuevosSemana;

    @Column(name = "ee_total_usuarios", nullable = false)
    @Min(value = 0, message = "El total de usuarios no puede ser negativo")
    private Integer totalUsuarios;

    @Column(name = "ee_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;
}
