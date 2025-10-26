package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasEstadoRepository extends JpaRepository<EstadisticasEstado, String> {

    Optional<EstadisticasEstado> findByEstado(String estado);

    @Query(value = "SELECT MAX(est_codigo) FROM Estadisticas_Estado", nativeQuery = true)
    String obtenerCodigo();

}
