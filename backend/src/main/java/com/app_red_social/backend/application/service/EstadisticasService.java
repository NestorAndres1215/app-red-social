package com.app_red_social.backend.application.service;

import com.app_red_social.backend.application.dto.estadisticas.EstadisticasResponse;
import com.app_red_social.backend.application.dto.estadisticas.TotalCantidadResponse;
import com.app_red_social.backend.domain.port.repository.EstadisticasRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.EstadisticasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasService implements EstadisticasUseCase {

    private final EstadisticasRepositoryPort estadisticasRepositoryPort;

    @Override
    public List<EstadisticasResponse> estadisticaModerador(Integer opcion) {
        return estadisticasRepositoryPort.estadisticaModerador(opcion);
    }

    @Override
    public List<TotalCantidadResponse> totalModerador(Integer opcion) {
        return estadisticasRepositoryPort.totalModerador(opcion);
    }
}
