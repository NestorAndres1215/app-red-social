package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.UsuarioActualResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioActualMapper {

    public List<UsuarioActualResponse> toList(List<Object[]> rows) {
        return rows.stream().map(this::toDTO).toList();
    }

    public UsuarioActualResponse toDTO(Object[] row) {
        return UsuarioActualResponse.builder()
                .codigoUsuario((String) row[0])
                .nombre((String) row[1])
                .apellido((String) row[2])
                .edad(row[3] != null ? ((Number) row[3]).intValue() : null)
                .fechaNacimiento(row[4] != null ? row[4].toString() : null)
                .genero((String) row[5])
                .nacionalidad((String) row[6])
                .presentacion((String) row[7])
                .photoUrl((String) row[8])
                .banner((byte[]) row[9])
                .perfil((byte[]) row[10])
                .correo((String) row[11])
                .telefono((String) row[12])
                .username((String) row[13])
                .rol((String) row[14])
                .ultimoLogin(row[15] != null ? row[15].toString() : null)
                .fechaCreacion(row[16] != null ? row[16].toString() : null)
                .codigoLogin((String) row[17])
                .estadoNombre((String) row[18])
                .estadoDescripcion((String) row[19])
                .build();
    }
}
