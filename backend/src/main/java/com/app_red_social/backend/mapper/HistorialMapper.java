package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.HistorialResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class HistorialMapper {

    public HistorialResponse toDto(Object[] row) {

        if (row == null || row.length < 6) return null;

        return HistorialResponse.builder()
                .codigoHistorial(safe(row[0]))
                .fechaHistorial(safe(row[1]))
                .horaHistorial(safe(row[2]))
                .usuarioHistorial(safe(row[3]))
                .estadoHistorial(safe(row[4]))
                .detalleHistorial(safe(row[5]))
                .build();
    }

    public List<HistorialResponse> toDtoList(List<Object[]> rows) {
        return rows.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .toList();
    }

    private String safe(Object value) {
        return value != null ? value.toString() : null;
    }

}
