package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, String> {

    @Query(value = "SELECT MAX(se_codigo) FROM Sesion", nativeQuery = true)
    String obtenerCodigo();

    List<Sesion> findByLogin_Username(String username);

}
