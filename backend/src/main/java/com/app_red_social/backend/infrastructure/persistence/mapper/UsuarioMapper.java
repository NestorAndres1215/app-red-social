package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.usuario.UsuarioListaResponse;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.LoginEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final LoginMapper loginMapper;

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;

        return Usuario.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .edad(entity.getEdad())
                .fechaNacimiento(entity.getFechaNacimiento())
                .banner(entity.getBanner())
                .perfil(entity.getPerfil())
                .provider(entity.getProvider())
                .genero(entity.getGenero())
                .nacionalidad(entity.getNacionalidad())
                .ciudad(entity.getCiudad())
                .pais(entity.getPais())
                .seguidores(entity.getSeguidores())
                .seguidos(entity.getSeguidos())
                .publicaciones(entity.getPublicaciones())
                .presentacion(entity.getPresentacion())
                .verificado(entity.getVerificado())
                .cuentaPrivada(entity.getCuentaPrivada())
                .login(loginMapper.toDomain(entity.getLogin()))
                .build();
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioEntity.builder()
                .codigo(usuario.getCodigo())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .perfil(usuario.getPerfil())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .edad(usuario.getEdad())
                .provider(usuario.getProvider())
                .cuentaPrivada(usuario.getCuentaPrivada())
                .nacionalidad(usuario.getNacionalidad())
                .seguidores(usuario.getSeguidores())
                .seguidos(usuario.getSeguidos())
                .publicaciones(usuario.getPublicaciones())
                .pais(usuario.getPais())
                .ciudad(usuario.getCiudad())
                .genero(usuario.getGenero())
                .banner(usuario.getBanner())
                .presentacion(usuario.getPresentacion())
                .verificado(usuario.getVerificado())
                .login(loginMapper.toEntity(usuario.getLogin()))
                .build();
    }


    public UsuarioListaResponse usuarioListado(Object[] row) {
        if (row == null) return null;

        return UsuarioListaResponse.builder()
                .codigo((String) row[0])
                .nombre((String) row[1])
                .apellido((String) row[2])
                .edad(row[3] != null ? String.valueOf(row[3]) : null)
                .fechaNacimiento(row[4] != null ? ((Date) row[4]).toLocalDate().toString() : null)
                .genero((String) row[5])
                .nacionalidad((String) row[6])
                .perfil((String) row[7])
                .username((String) row[8])
                .correo((String) row[9])
                .telefono((String) row[10])
                .fechaRegistro(row[11] != null ? ((Timestamp) row[11]).toLocalDateTime().toString() : null)
                .ultimoLogin(row[12] != null ? ((Timestamp) row[12]).toLocalDateTime().toString() : null)
                .estado((String) row[13])
                .build();
    }

    public List<UsuarioListaResponse> usuarioListadoList(List<Object[]> rows) {
        return rows.stream()
                .map(this::usuarioListado)
                .toList();
    }
}