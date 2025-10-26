package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadisticasSemanaRepository extends JpaRepository<EstadisticasSemana, String> {

    // Buscar por número de semana y año (ej: 32, 2025)
    Optional<EstadisticasSemana> findByNumeroSemanaAndMesAnio(Integer numeroSemana, String mesAnio);

    // Obtener la última semana registrada (para calcular % cambio)
    Optional<EstadisticasSemana> findTopByOrderByNumeroSemanaDesc();

}