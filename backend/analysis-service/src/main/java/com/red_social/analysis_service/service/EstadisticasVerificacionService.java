package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasVerificacion;
import com.red_social.analysis_service.repository.EstadisticasVerificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasVerificacionService {

    private final EstadisticasVerificacionRepository estadisticasVerificacionRepository;

    public List<EstadisticasVerificacion> listar() {
        return estadisticasVerificacionRepository.findAll();
    }

    public EstadisticasVerificacion listarPorCodigo(String codigo) {
        return estadisticasVerificacionRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Verificación no encontrada con código: " + codigo));
    }

    public EstadisticasVerificacion listarPorEstado(String estado) {
        return estadisticasVerificacionRepository.findByEstado(estado)
                .orElseThrow(() -> new IllegalArgumentException("Verificación no encontrada con estado: " + estado));
    }
}
