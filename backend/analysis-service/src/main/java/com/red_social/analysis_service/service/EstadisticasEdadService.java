package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasEdad;
import com.red_social.analysis_service.model.EstadisticasGenero;
import com.red_social.analysis_service.repository.EstadisticasEdadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EstadisticasEdadService {

    private final EstadisticasEdadRepository estadisticasEdadRepository;


    public List<EstadisticasEdad> listar() {
        return estadisticasEdadRepository.findAll();
    }

    public EstadisticasEdad listarId(String codigo) {
        return estadisticasEdadRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + codigo));
    }

    public EstadisticasEdad listarEdad(String rangoEdad) {
        return estadisticasEdadRepository.findByRangoEdad(rangoEdad)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + rangoEdad));
    }
}
