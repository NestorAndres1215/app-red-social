package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.UsuarioListaResponse;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioListaResponse toDto(Object[] row) {
        if (row == null) return null;

        return UsuarioListaResponse.builder()
                .codigo(toStr(row[0]))
                .nombre(toStr(row[1]))
                .apellido(toStr(row[2]))
                .edad(toStr(row[3]))
                .fechaNacimiento(toStr(row[4]))
                .genero(toStr(row[5]))
                .nacionalidad(toStr(row[6]))
                .perfil(toStrOrNull(row, 7))
                .ciudad(toStrOrNull(row, 8))
                .pais(toStrOrNull(row, 9))
                .banner(toStrOrNull(row, 10))
                .foto(toStrOrNull(row, 11))
                .presentacion(toStrOrNull(row, 12))
                .cuentaPrivada(toStrOrNull(row, 13))
                .publicaciones(toStrOrNull(row, 14))
                .seguidores(toStrOrNull(row, 15))
                .seguidos(toStrOrNull(row, 16))
                .verificado(toStrOrNull(row, 17))
                .username(toStrOrNull(row, 18))
                .correo(toStrOrNull(row, 19))
                .telefono(toStrOrNull(row, 20))
                .rol(toStrOrNull(row, 21))
                .fechaRegistro(toStrOrNull(row, 22))
                .ultimoLogin(toStrOrNull(row, 23))
                .estado(toStrOrNull(row, 24))
                .build();
    }

    public List<UsuarioListaResponse> toDtoList(List<Object[]> rows) {
        return rows.stream()
                .map(this::toDto)
                .toList();
    }

    private String toStr(Object value) {
        return value != null ? value.toString() : null;
    }

    private String toStrOrNull(Object[] row, int index) {
        return index < row.length ? toStr(row[index]) : null;
    }
}
