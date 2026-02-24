package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.EstadoUsuario;
import com.app_red_social.backend.infrastructure.persistence.entity.EstadoUsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {


    public EstadoUsuario toDomain(EstadoUsuarioEntity entity) {
        if (entity == null) return null;
        return EstadoUsuario.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public EstadoUsuarioEntity toEntity(EstadoUsuario estado) {
        if (estado == null) return null;
        return EstadoUsuarioEntity.builder()
                .codigo(estado.getCodigo())
                .nombre(estado.getNombre())
                .descripcion(estado.getDescripcion())
                .build();
    }
}
