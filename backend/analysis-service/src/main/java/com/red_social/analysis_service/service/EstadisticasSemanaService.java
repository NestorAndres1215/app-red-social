package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasSemana;
import com.red_social.analysis_service.repository.EstadisticasSemanaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasSemanaService {

    private final EstadisticasSemanaRepository estadisticasSemanaRepository;

    // Listar todas las semanas
    public List<EstadisticasSemana> listar() {
        return estadisticasSemanaRepository.findAll();
    }

    // Buscar semana por código
    public EstadisticasSemana listarPorCodigo(String codigo) {
        return estadisticasSemanaRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Semana no encontrada con código: " + codigo));
    }

    // Buscar semana por número y mes-año
    public EstadisticasSemana listarPorNumeroYMes(Integer numeroSemana, String mesAnio) {
        return estadisticasSemanaRepository.findByNumeroSemanaAndMesAnio(numeroSemana, mesAnio)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Semana no encontrada: número " + numeroSemana + ", mes-año " + mesAnio));
    }

    // Obtener la última semana registrada
    public EstadisticasSemana obtenerUltimaSemana() {
        return estadisticasSemanaRepository.findTopByOrderByNumeroSemanaDesc()
                .orElseThrow(() -> new IllegalArgumentException("No hay semanas registradas"));
    }
}
