package com.red_social.analysis_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasEstado {

    @Id
    @Column(name = "est_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @Column(name = "est_estado", length = 255, nullable = false)
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @Column(name = "est_nuevo_semana", nullable = false)
    @NotNull(message = "El número de nuevos de la semana es obligatorio")
    @Min(value = 0, message = "El número de nuevos debe ser mayor o igual a 0")
    private Integer nuevoSemana;

    @Column(name = "est_porcentaje", nullable = false)
    @NotNull(message = "El porcentaje es obligatorio")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor que 100")
    private Double porcentaje;

    @Column(name = "eest_total_usuarios", nullable = false)
    @NotNull(message = "El total de usuarios es obligatorio")
    @Min(value = 0, message = "El total de usuarios debe ser mayor o igual a 0")
    private Integer totalUsuarios;

    @Column(name = "eest_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;

}
