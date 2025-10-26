package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasPais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasPaisRepository extends JpaRepository<EstadisticasPais,String> {

    Optional<EstadisticasPais> findByPais(String pais);

    @Query(value = "SELECT MAX(ep_codigo) FROM Estadisticas_Pais", nativeQuery = true)
    String obtenerCodigo();
}

