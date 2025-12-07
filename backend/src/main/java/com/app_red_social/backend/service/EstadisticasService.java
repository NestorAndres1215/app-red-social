package com.app_red_social.backend.service;

import com.app_red_social.backend.dto.response.EstadisticasResponse;
import com.app_red_social.backend.mapper.EstadisticasModeradorMapper;
import com.app_red_social.backend.repository.ModeradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasService {


    private final ModeradorRepository moderadorRepository;
    private final EstadisticasModeradorMapper estadisticasModerador;

    public List<EstadisticasResponse> estadisticaModerador(Integer opcion) {

        List<Object[]> rows = moderadorRepository.estadisticasModerador(opcion);

        return rows.stream()
                .map(estadisticasModerador::porcentajeHistorial)
                .toList();
    }
}
