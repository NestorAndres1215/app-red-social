package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {


    public Rol toDomain(RolEntity entity) {
        if (entity == null) return null;
        return Rol.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public RolEntity toEntity(Rol rol) {
        if (rol == null) return null;
        return RolEntity.builder()
                .codigo(rol.getCodigo())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .build();
    }
}
