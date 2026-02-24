package com.app_red_social.backend.infrastructure.persistence.repository;


import com.app_red_social.backend.infrastructure.persistence.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaLoginRepository extends JpaRepository<LoginEntity, String> {

    @Query(value = "SELECT MAX(lg_codigo) FROM Login", nativeQuery = true)
    String obtenerCodigo();

    Optional<LoginEntity> findByUsername(String username);

    Optional<LoginEntity> findByEmail(String email);

    Optional<LoginEntity> findByTelefono(String telefono);

}