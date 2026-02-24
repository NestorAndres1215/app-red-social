package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.usuario.RoleUserStatsResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoleUserStatsMapper {

    public RoleUserStatsResponse map(Object[] record) {

        int cantidad = 0;
        double porcentaje = 0.0;
        String periodo = "";

        if (record[0] instanceof Number) {
            cantidad = ((Number) record[0]).intValue();
        }

        if (record[1] instanceof Number) {
            porcentaje = ((Number) record[1]).doubleValue();
        }

        if (record[2] != null) {
            periodo = record[2].toString();
        }

        return RoleUserStatsResponse.builder()
                .cantidad(cantidad)
                .porcentaje(porcentaje)
                .periodo(periodo)
                .build();
    }

    public List<RoleUserStatsResponse> toList(List<Object[]> records) {
        return records.stream()
                .map(this::map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
