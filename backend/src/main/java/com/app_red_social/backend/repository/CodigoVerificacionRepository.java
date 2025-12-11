package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.CodigoVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacion, String> {

    Optional<CodigoVerificacion> findByCorreo(String correo);
    Optional<CodigoVerificacion> findByCodigoVerificacion(String codigo_verificacion);

    @Query(value = "SELECT MAX(cv_codigo) FROM Codigo_Verificacion", nativeQuery = true)
    String obtenerUltimoCodigo();
}