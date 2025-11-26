package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.RoleUserStatsResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleUserStatsMapper {

    public static RoleUserStatsResponse map(Object[] record) {
        if (record == null || record.length < 3) {
            return null;
        }

        int cantidad = 0;
        double porcentaje = 0.0;
        String periodo = "";

        try {
            if (record[0] instanceof Number) {
                cantidad = ((Number) record[0]).intValue();
            } else if (record[0] != null) {
                cantidad = Integer.parseInt(record[0].toString());
            }

            if (record[1] instanceof Number) {
                porcentaje = ((Number) record[1]).doubleValue();
            } else if (record[1] != null) {
                porcentaje = Double.parseDouble(record[1].toString());
            }

            if (record[2] != null) {
                periodo = record[2].toString();
            }

        } catch (NumberFormatException e) {
            // En caso de error, dejar cantidad y porcentaje en 0
            cantidad = 0;
            porcentaje = 0.0;
        }

        return RoleUserStatsResponse.builder()
                .cantidad(cantidad)
                .porcentaje(porcentaje)
                .periodo(periodo)
                .build();
    }

    public static List<RoleUserStatsResponse> toList(List<Object[]> records) {
        return records.stream()
                .map(RoleUserStatsMapper::map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
