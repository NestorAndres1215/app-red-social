package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasEdad;
import com.red_social.analysis_service.model.EstadisticasRoles;
import com.red_social.analysis_service.model.EstadisticasVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasRolesRepository extends JpaRepository<EstadisticasRoles, String> {

    @Query(value = "SELECT MAX(er_codigo) FROM Estadisticas_Roles", nativeQuery = true)
    String obtenerCodigo();
    Optional<EstadisticasRoles> findByRoles(String verificacion);

}
