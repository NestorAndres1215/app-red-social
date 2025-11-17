package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion, String> {

    @Query(value = "SELECT MAX(se_codigo) FROM Sesion", nativeQuery = true)
    String obtenerCodigo();

    Optional<Sesion> findByLogin_Username(String username);

}