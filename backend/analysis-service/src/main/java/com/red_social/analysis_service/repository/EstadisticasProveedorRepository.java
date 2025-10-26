package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EstadisticasEdad;
import com.red_social.analysis_service.model.EstadisticasProveedor;
import com.red_social.analysis_service.model.EstadisticasRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadisticasProveedorRepository extends JpaRepository<EstadisticasProveedor, String> {
    @Query(value = "SELECT MAX(epr_codigo) FROM Estadisticas_Proveedor", nativeQuery = true)
    String obtenerCodigo();
    Optional<EstadisticasProveedor> findByProveedor(String proveedor);
}
