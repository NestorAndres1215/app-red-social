package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModeradorRepository extends JpaRepository<Moderador,String> {

    Optional<Moderador> findByLogin_Username(String username);

    Optional<Moderador> findByLogin_Email(String email);

    Optional<Moderador> findByLogin_Telefono(String telefono);

    Optional<Moderador> findByLogin_Codigo(String usuarioCodigo);
    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerCodigo();


}
