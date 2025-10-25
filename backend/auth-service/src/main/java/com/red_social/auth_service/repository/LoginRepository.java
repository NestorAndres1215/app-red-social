package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

    Optional<Login> findByUsername(String username);

    Optional<Login> findByEmail(String email);

    Optional<Login> findByTelefono(String telefono);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByTelefono(String telefono);

    @Query(value = "SELECT MAX(lg_codigo) FROM Login", nativeQuery = true)
    String obtenerCodigo();
}

