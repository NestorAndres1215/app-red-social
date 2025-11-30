package com.app_red_social.backend.mapper;

import com.app_red_social.backend.dto.response.UsuarioListaResponse;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioListaResponse toDto(Object[] row) {
        if (row == null) return null;

        return UsuarioListaResponse.builder()
                .codigo(toStr(row[0]))             // a.ad_codigo
                .nombre(toStr(row[1]))             // a.ad_nombre
                .apellido(toStr(row[2]))           // a.ad_apellido
                .edad(toStr(row[3]))               // a.ad_edad
                .fechaNacimiento(toStr(row[4]))    // a.ad_fecha_nacimiento
                .genero(toStr(row[5]))             // a.ad_genero
                .nacionalidad(toStr(row[6]))       // a.ad_nacionalidad
                .perfil(toStr(row[7]))             // a.ad_perfil

                .username(toStr(row[8]))           // l.lg_username
                .correo(toStr(row[9]))             // l.lg_correo
                .telefono(toStr(row[10]))          // l.lg_telefono

                .fechaRegistro(toStr(row[11]))     // l.lg_fecha_registro
                .ultimoLogin(toStr(row[12]))       // l.lg_ultimo_login
                .estado(toStr(row[13]))            // e.st_nombre
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
