package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasVerificacionRepository  extends JpaRepository<EstadisticasVerificacion,String> {
    Optional<EstadisticasVerificacion> findByEstado(String verificacion);

    @Query(value = "SELECT MAX(ev_codigo) FROM Estadisticas_Verificacion", nativeQuery = true)
    String obtenerCodigo();
}