package com.red_social.analysis_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasPais {

    @Id
    @Column(name = "ep_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;


    @Column(name = "ep_pais", length = 100, nullable = false)
    @NotBlank(message = "El país no puede estar vacío")
    private String pais;

    @Column(name = "ep_porcentaje", nullable = false)
    @NotNull(message = "El porcentaje es obligatorio")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    private Double porcentaje;

    @Column(name = "ep_total_usuarios", nullable = false)
    @Min(value = 0, message = "El total de usuarios no puede ser negativo")
    private Integer totalUsuarios; // puede ser null

    @Column(name = "ep_nuevo_semana", nullable = false)
    @Min(value = 0, message = "Los nuevos usuarios de la semana no pueden ser negativos")
    private Integer nuevosSemana; // puede ser null

    @Column(name = "ep_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;

}
