package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.historial.HistorialResponse;
import com.app_red_social.backend.domain.model.HistorialUsuario;
import com.app_red_social.backend.infrastructure.persistence.entity.HistorialUsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistorialMapper {

    private final LoginMapper loginMapper;

    public HistorialUsuario toDomain(HistorialUsuarioEntity entity) {
        if (entity == null) return null;

        return HistorialUsuario.builder()
                .codigo(entity.getCodigo())
                .fechaRegistro(entity.getFechaRegistro())
                .login(loginMapper.toDomain(entity.getLogin()))
                .modulo(entity.getModulo())
                .titulo(entity.getTitulo())
                .detalle(entity.getDetalle())
                .estado(entity.getEstado())
                .build();
    }

    public HistorialUsuarioEntity toEntity(HistorialUsuario historial) {
        if (historial == null) return null;

        return HistorialUsuarioEntity.builder()
                .codigo(historial.getCodigo())
                .fechaRegistro(historial.getFechaRegistro())
                .login(loginMapper.toEntity(historial.getLogin()))
                .modulo(historial.getModulo())
                .titulo(historial.getTitulo())
                .detalle(historial.getDetalle())
                .estado(historial.getEstado())
                .build();
    }

    public HistorialResponse listarHistorial(Object[] row) {
        return HistorialResponse.builder()
                .codigoHistorial(row[0] != null ? row[0].toString() : null)
                .fechaHistorial(row[1] != null ? row[1].toString() : null)
                .horaHistorial(row[2] != null ? row[2].toString() : null)
                .usuarioHistorial(row[3] != null ? row[3].toString() : null)
                .estadoHistorial(row[4] != null ? row[4].toString() : null)
                .tituloHistorial(row[5] != null ? row[5].toString() : null)
                .moduloHistorial(row[6] != null ? row[6].toString() : null)
                .detalleHistorial(row[7] != null ? row[7].toString() : null)
                .build();
    }
}