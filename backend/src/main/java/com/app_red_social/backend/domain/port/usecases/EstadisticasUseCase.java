package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.application.dto.estadisticas.EstadisticasResponse;
import com.app_red_social.backend.application.dto.estadisticas.TotalCantidadResponse;

import java.util.List;

public interface EstadisticasUseCase {

    List<EstadisticasResponse> estadisticaModerador(Integer opcion);

    List<TotalCantidadResponse> totalModerador(Integer opcion);
}
