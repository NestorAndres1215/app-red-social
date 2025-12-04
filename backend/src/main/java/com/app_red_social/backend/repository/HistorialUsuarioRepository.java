package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.HistorialUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface HistorialUsuarioRepository extends JpaRepository<HistorialUsuario, String> {

    @Query(value = "SELECT MAX(hu_codigo) FROM historial_usuario", nativeQuery = true)
    String obtenerUltimoCodigo();

    @Query(value = "CALL sp_historial_usuario(:opcion,:username, :estado)", nativeQuery = true)
    List<Object[]> obtenerHistorial(
            @Param("opcion") Integer opcion,
            @Param("username") String username,
            @Param("estado") String estado
    );

}

