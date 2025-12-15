package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Suspensiones;
import com.app_red_social.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface SuspensionesRepository extends JpaRepository<Suspensiones, String> {

    @Query(value = "SELECT MAX(sus_codigo) FROM Suspensiones", nativeQuery = true)
    String obtenerUltimoCodigo();

    Optional<Suspensiones> findByUsuario(Usuario usuario);

}