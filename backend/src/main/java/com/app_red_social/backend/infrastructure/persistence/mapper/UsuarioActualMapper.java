package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioActualMapper {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UsuarioActualResponse toDTO(Object[] row) {
        return UsuarioActualResponse.builder()
                .codigoUsuario((String) row[0])
                .nombre((String) row[1])
                .apellido((String) row[2])
                .edad(row[3] != null ? ((Number) row[3]).intValue() : null)
                .fechaNacimiento(row[4] != null
                        ? ((Date) row[4]).toLocalDate().format(DATE_FORMATTER)
                        : null)
                .genero((String) row[5])
                .nacionalidad((String) row[6])
                .presentacion((String) row[7])
                .photoUrl((String) row[8])
                .banner((String) row[9])
                .perfil((String) row[10])
                .correo((String) row[11])
                .telefono((String) row[12])
                .username((String) row[13])
                .rol((String) row[14])
                .ultimoLogin(row[15] != null
                        ? ((Timestamp) row[15]).toLocalDateTime().format(DATETIME_FORMATTER)
                        : null)
                .fechaCreacion(row[16] != null
                        ? ((Timestamp) row[16]).toLocalDateTime().format(DATETIME_FORMATTER)
                        : null)
                .codigoLogin((String) row[17])
                .estadoNombre((String) row[18])
                .estadoDescripcion((String) row[19])
                .build();
    }

    public List<UsuarioActualResponse> toList(List<Object[]> rows) {
        return rows.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}