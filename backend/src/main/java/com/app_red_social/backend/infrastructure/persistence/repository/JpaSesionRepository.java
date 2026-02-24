package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaSesionRepository extends JpaRepository<SesionEntity,String> {

    @Query(value = "SELECT MAX(se_codigo) FROM Sesion", nativeQuery = true)
    String obtenerCodigo();

    Optional<SesionEntity> findByLogin_Username(String username);

}
