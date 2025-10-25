package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario,String> {
        Optional<EstadoUsuario> findByNombre(String nombre);

        @Query(value = "SELECT MAX(st_codigo) FROM EstadoUsuario", nativeQuery = true)
        String obtenerCodigo();
}
