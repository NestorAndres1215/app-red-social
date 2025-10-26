package com.red_social.analysis_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estadisticas_proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadisticasProveedor {

    @Id
    @Column(name = "epr_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;

    @Column(name = "epr_proveedor", length = 100, nullable = false)
    @NotBlank(message = "El país no puede estar vacío")
    private String proveedor;

    @Column(name = "epr_porcentaje", nullable = false)
    @NotNull(message = "El porcentaje es obligatorio")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    private Double porcentaje;

    @Column(name = "epr_total_usuarios", nullable = false)
    @Min(value = 0, message = "El total de usuarios no puede ser negativo")
    private Integer totalUsuarios; // puede ser null

    @Column(name = "epr_nuevo_semana", nullable = false)
    @Min(value = 0, message = "Los nuevos usuarios de la semana no pueden ser negativos")
    private Integer nuevoSemana; // puede ser null

    @Column(name = "epr_fecha_actualizacion", nullable = false)
    @NotNull(message = "La fecha de actualización es obligatoria")
    private LocalDateTime fechaActualizacion;
}
