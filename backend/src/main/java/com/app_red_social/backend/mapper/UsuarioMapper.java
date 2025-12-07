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
                .perfil(toStr(row[7]))
                .username(toStr(row[8]))
                .correo(toStr(row[9]))
                .telefono(toStr(row[10]))
                .fechaRegistro(toStr(row[11]))
                .ultimoLogin(toStr(row[12]))
                .estado(toStr(row[13]))
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


}
