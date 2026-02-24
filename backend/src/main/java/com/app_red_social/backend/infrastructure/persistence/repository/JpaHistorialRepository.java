package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.HistorialUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaHistorialRepository  extends JpaRepository<HistorialUsuarioEntity, String> {

    @Query(value = "SELECT MAX(hu_codigo) FROM historial_usuario", nativeQuery = true)
    String obtenerUltimoCodigo();

    @Query(value = "CALL sp_historial_usuario(:opcion,:username, :estado)", nativeQuery = true)
    List<Object[]> obtenerHistorial(
            @Param("opcion") Integer opcion,
            @Param("username") String username,
            @Param("estado") String estado
    );

}
