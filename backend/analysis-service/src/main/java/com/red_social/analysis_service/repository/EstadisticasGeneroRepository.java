package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasGenero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasGeneroRepository extends JpaRepository<EstadisticasGenero,String> {
    Optional<EstadisticasGenero> findByGenero(String genero);

    @Query(value = "SELECT MAX(eg_codigo) FROM Estadisticas_Genero", nativeQuery = true)
    String obtenerCodigo();
}
