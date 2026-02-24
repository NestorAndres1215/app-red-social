package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.estadisticas.EstadisticasResponse;
import com.app_red_social.backend.application.dto.estadisticas.TotalCantidadResponse;
import org.springframework.stereotype.Component;

@Component
public class EstadisticasMapper {

    public EstadisticasResponse porcentajeHistorial(Object[] row) {

        return EstadisticasResponse.builder()
                .atributo(row[0] != null ? row[0].toString() : null)
                .total(row[1] != null ? (row[1].toString()) : null)
                .porcentaje(row[2] != null ? row[2].toString() : "0%")
                .build();
    }

    public TotalCantidadResponse totalHistorial(Object[] row) {

        return TotalCantidadResponse.builder()
                .total(row[0] != null ? (row[0].toString()) : null)
                .build();
    }

}


