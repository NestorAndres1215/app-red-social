package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.Moderador;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.infrastructure.persistence.entity.ModeradorEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModeradorMapper {

    private final LoginMapper loginMapper;

    public Moderador toDomain(ModeradorEntity entity) {
        if (entity == null) return null;

        return Moderador.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .edad(entity.getEdad())
                .fechaNacimiento(entity.getFechaNacimiento())
                .nacionalidad(entity.getNacionalidad())
                .perfil(entity.getPerfil())
                .genero(entity.getGenero())
                .login(loginMapper.toDomain(entity.getLogin()))
                .build();
    }

    public ModeradorEntity toEntity(Moderador moderador) {
        if (moderador == null) return null;

        return ModeradorEntity.builder()
                .codigo(moderador.getCodigo())
                .nombre(moderador.getNombre())
                .apellido(moderador.getApellido())
                .edad(moderador.getEdad())
                .fechaNacimiento(moderador.getFechaNacimiento())
                .nacionalidad(moderador.getNacionalidad())
                .perfil(moderador.getPerfil())
                .genero(moderador.getGenero())
                .login(loginMapper.toEntity(moderador.getLogin()))
                .build();
    }
}