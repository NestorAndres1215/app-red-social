package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasEstado;

import com.red_social.analysis_service.repository.EstadisticasEstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasEstadoService {
    private final EstadisticasEstadoRepository estadisticasEstadoRepository;


    public List<EstadisticasEstado> listar() {
        return estadisticasEstadoRepository.findAll();
    }

    public EstadisticasEstado listarId(String codigo) {
        return estadisticasEstadoRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + codigo));
    }

    public EstadisticasEstado listarEstado(String rangoEdad) {
        return estadisticasEstadoRepository.findByEstado(rangoEdad)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + rangoEdad));
    }
}
