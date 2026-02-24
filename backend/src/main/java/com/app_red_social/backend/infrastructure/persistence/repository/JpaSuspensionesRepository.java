package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.SuspensionesEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaSuspensionesRepository extends JpaRepository<SuspensionesEntity,String> {

    @Query(value = "SELECT MAX(sus_codigo) FROM Suspensiones", nativeQuery = true)
    String obtenerUltimoCodigo();

    Optional<SuspensionesEntity> findByUsuario(UsuarioEntity usuario);

}
