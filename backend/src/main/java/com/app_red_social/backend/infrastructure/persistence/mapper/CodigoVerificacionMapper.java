package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.CodigoVerificacion;
import com.app_red_social.backend.infrastructure.persistence.entity.CodigoVerificacionEntity;
import org.springframework.stereotype.Component;

@Component
public class CodigoVerificacionMapper {

    public CodigoVerificacion toDomain(CodigoVerificacionEntity entity) {
        if (entity == null) return null;

        return CodigoVerificacion.builder()
                .codigo(entity.getCodigo())
                .correo(entity.getCorreo())
                .usuario(entity.getUsuario())
                .tipoVerificacion(entity.getTipoVerificacion())
                .codigoVerificacion(entity.getCodigoVerificacion())
                .fechaGeneracion(entity.getFechaGeneracion())
                .build();
    }

    public CodigoVerificacionEntity toEntity(CodigoVerificacion domain) {
        if (domain == null) return null;

        return CodigoVerificacionEntity.builder()
                .codigo(domain.getCodigo())
                .correo(domain.getCorreo())
                .usuario(domain.getUsuario())
                .tipoVerificacion(domain.getTipoVerificacion())
                .codigoVerificacion(domain.getCodigoVerificacion())
                .fechaGeneracion(domain.getFechaGeneracion())
                .build();
    }
}