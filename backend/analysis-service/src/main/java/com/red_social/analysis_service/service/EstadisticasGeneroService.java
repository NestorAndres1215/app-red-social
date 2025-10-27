package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasGenero;
import com.red_social.analysis_service.repository.EstadisticasGeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EstadisticasGeneroService {

    private final EstadisticasGeneroRepository estadisticasGeneroRepository;

    public List<EstadisticasGenero> listar() {
        return estadisticasGeneroRepository.findAll();
    }

    public EstadisticasGenero listarId(String codigo) {
        return estadisticasGeneroRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + codigo));
    }

    public EstadisticasGenero listarGenero(String genero) {
        return estadisticasGeneroRepository.findByGenero(genero)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + genero));
    }

}
