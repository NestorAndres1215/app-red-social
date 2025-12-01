package com.app_red_social.backend.mapper;


import com.app_red_social.backend.dto.response.AdministradorActualResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdministradorActualMapper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static AdministradorActualResponse toDto(Object[] row) {
        return AdministradorActualResponse.builder()
                .codigoAdministrador((String) row[0])
                .nombre((String) row[1])
                .apellido((String) row[2])
                .nombreCompleto((String) row[3])
                .edad(row[4] != null ? ((Number) row[4]).intValue() : null)

                // ⬇ fechaNacimiento como String
                .fechaNacimiento(row[5] != null ? DATE_FORMAT.format((Date) row[5]) : null)

                .fechaNacimientoFormateada(row[6] != null ? row[6].toString() : null)
                .edadCalculada(row[7] != null ? ((Number) row[7]).intValue() : null)
                .genero((String) row[8])
                .nacionalidad((String) row[9])
                .fotoPerfil((byte[]) row[10])
                .codigoLogin((String) row[11])
                .correo((String) row[12])
                .telefono((String) row[13])
                .username((String) row[14])
                .rol((String) row[15])
                .estadoUsuario((String) row[16])

                // ⬇ ultimoLogin y fechaRegistro como String
                .ultimoLogin(row[17] != null ? DATETIME_FORMAT.format((Timestamp) row[17]) : null)
                .fechaRegistro(row[18] != null ? DATETIME_FORMAT.format((Timestamp) row[18]) : null)
                .build();
    }
    public List<AdministradorActualResponse> toList(List<Object[]> rows) {
        return rows.stream()
                .map(AdministradorActualMapper::toDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
