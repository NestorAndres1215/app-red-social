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
@Table(name = "estadisticas_semana")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasSemana {

    @Id
    @Column(name = "es_codigo", nullable = false, length = 36)
    @NotBlank(message = "El código es obligatorio")
    private String codigo; // UUID o código secuencial

    @Column(name = "es_mes_anio", nullable = false, length = 6)
    @NotBlank(message = "El mes y año es obligatorio (formato MMYYYY)")
    private String mesAnio;

    @Column(name = "es_numero_semana", nullable = false)
    @NotNull(message = "El número de semana es obligatorio")
    @Min(value = 1, message = "El número de semana debe ser mínimo 1")
    @Max(value = 53, message = "El número de semana no puede ser mayor a 53")
    private Integer numeroSemana;

    @Column(name = "es_fecha_registro", nullable = false)
    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    @Column(name = "es_primer_dia_semana", nullable = false)
    @NotNull(message = "El primer día de semana es obligatorio")
    private LocalDateTime primerDiaSemana;

    @Column(name = "es_ultimo_dia_semana", nullable = false)
    @NotNull(message = "El último día de semana es obligatorio")
    private LocalDateTime ultimoDiaSemana;

    @Column(name = "es_total_registros", nullable = false)
    @NotNull(message = "El total de registros es obligatorio")
    @Min(value = 0, message = "El total de registros no puede ser negativo")
    private Integer totalRegistros;

    @Column(name = "es_porcentaje_cambio")
    @DecimalMin(value = "-100.0", message = "El porcentaje no puede ser menor a -100%")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100%")
    private Double porcentajeCambio;

    @Column(name = "es_fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}
