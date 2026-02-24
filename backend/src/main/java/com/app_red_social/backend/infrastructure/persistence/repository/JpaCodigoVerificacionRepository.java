package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.CodigoVerificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaCodigoVerificacionRepository extends JpaRepository<CodigoVerificacionEntity, String> {

    Optional<CodigoVerificacionEntity> findByCorreo(String correo);

    Optional<CodigoVerificacionEntity> findByCodigoVerificacion(String codigo_verificacion);

    @Query(value = "SELECT MAX(cv_codigo) FROM Codigo_Verificacion", nativeQuery = true)
    String obtenerUltimoCodigo();

}
