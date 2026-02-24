package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.application.dto.estadisticas.EstadisticasResponse;
import com.app_red_social.backend.application.dto.estadisticas.TotalCantidadResponse;
import com.app_red_social.backend.domain.port.repository.EstadisticasRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.mapper.EstadisticasMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaModeradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstadisticasRepositoryAdapter implements EstadisticasRepositoryPort {

    private final JpaModeradorRepository repository;
    private  final EstadisticasMapper mapper;


    @Override
    public List<EstadisticasResponse> estadisticaModerador(Integer opcion) {
        List<Object[]> rows = repository.estadisticasModerador(opcion);
        return rows.stream()
                .map(mapper::porcentajeHistorial)
                .toList();
    }

    public List<TotalCantidadResponse> totalModerador(Integer opcion) {
        List<Object[]> rows = repository.estadisticasModerador(opcion);
        return rows.stream()
                .map(mapper::totalHistorial)
                .toList();
    }
}
