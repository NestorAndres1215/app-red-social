package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {

    Optional<Login> findByUsername(String username);

    Optional<Login> findByEmail(String email);

    Optional<Login> findByTelefono(String telefono);

    @Query(value = "SELECT MAX(lg_codigo) FROM Login", nativeQuery = true)
    String obtenerCodigo();
}
