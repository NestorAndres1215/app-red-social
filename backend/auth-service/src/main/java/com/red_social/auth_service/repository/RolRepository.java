package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface RolRepository  extends JpaRepository<Rol,String> {
    Optional<Rol> findByNombre(String nombre);
    @Query(value = "SELECT MAX(tr_codigo) FROM Rol", nativeQuery = true)
    String obtenerCodigo();
}
