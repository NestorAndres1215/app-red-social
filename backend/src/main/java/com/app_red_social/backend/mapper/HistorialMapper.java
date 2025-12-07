package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.HistorialResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class HistorialMapper {

    public List<HistorialResponse> toDtoList(List<Object[]> rows) {
        return rows.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .toList();
    }

    public HistorialResponse toDto(Object[] row) {
        return HistorialResponse.builder()
                .codigoHistorial(row[0] != null ? row[0].toString() : null)
                .fechaHistorial(row[1] != null ? row[1].toString() : null)
                .horaHistorial(row[2] != null ? row[2].toString() : null)
                .usuarioHistorial(row[3] != null ? row[3].toString() : null)
                .estadoHistorial(row[4] != null ? row[4].toString() : null)
                .tituloHistorial(row[5] != null ? row[5].toString() : null)
                .moduloHistorial(row[6] != null ? row[6].toString() : null)
                .detalleHistorial(row[7] != null ? row[7].toString() : null)
                .build();
    }

}
