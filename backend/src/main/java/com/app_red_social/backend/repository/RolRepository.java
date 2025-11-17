package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Rol;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, String> {

    @Query(value = "SELECT MAX(rl_codigo) FROM Rol", nativeQuery = true)
    String obtenerCodigo();

    Optional<Rol> findByNombre(String nombre);

}
