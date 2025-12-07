package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.EstadisticasResponse;
import com.app_red_social.backend.dto.response.TotalCantidadResponse;
import org.springframework.stereotype.Component;

@Component
public class EstadisticasModeradorMapper {

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


