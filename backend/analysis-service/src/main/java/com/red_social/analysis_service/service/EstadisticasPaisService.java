package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasGenero;
import com.red_social.analysis_service.model.EstadisticasPais;
import com.red_social.analysis_service.repository.EstadisticasGeneroRepository;
import com.red_social.analysis_service.repository.EstadisticasPaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasPaisService {

    private final EstadisticasPaisRepository estadisticasPaisRepository;

    public List<EstadisticasPais> listar() {
        return estadisticasPaisRepository.findAll();
    }

    public EstadisticasPais listarId(String codigo) {
        return estadisticasPaisRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + codigo));
    }

    public EstadisticasPais listarPais(String pais) {
        return estadisticasPaisRepository.findByPais(pais)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + pais));
    }
}
