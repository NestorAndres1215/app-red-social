package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, String> {

    Optional<Administrador> findByLogin_Username(String username);

    Optional<Administrador> findByLogin_Email(String email);

    Optional<Administrador> findByLogin_Telefono(String telefono);

    Optional<Administrador> findByLogin_Codigo(String usuarioCodigo);

    @Query(value = "SELECT MAX(ad_codigo) FROM Administrador", nativeQuery = true)
    String obtenerCodigo();
}
