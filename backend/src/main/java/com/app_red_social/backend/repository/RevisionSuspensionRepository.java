package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.RevisionSuspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RevisionSuspensionRepository extends JpaRepository<RevisionSuspension, String> {
    /*@Query(value = "CALL sp_listar_revisiones_por_correo(:correo_usuario)", nativeQuery = true)
        List<Object[]> listarPorCorreo(@Param("correo_usuario") String correo);
    */
    @Query(value = "SELECT MAX(rs_codigo) FROM Revisiones_Suspension", nativeQuery = true)
    String obtenerUltimoCodigo();
}