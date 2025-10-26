package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasEdad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasEdadRepository extends JpaRepository<EstadisticasEdad, String> {

    Optional<EstadisticasEdad> findByRangoEdad(String rangoEdad);

    @Query(value = "SELECT MAX(ee_codigo) FROM Estadisticas_Edad", nativeQuery = true)
    String obtenerCodigo();
}
