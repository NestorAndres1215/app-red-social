package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario, String> {

    @Query(value = "SELECT MAX(st_codigo) FROM EstadoUsuario", nativeQuery = true)
    String obtenerCodigo();

    Optional<EstadoUsuario> findByNombre(String nombre);
}
