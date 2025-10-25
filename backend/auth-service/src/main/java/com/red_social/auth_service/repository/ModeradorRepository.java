package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeradorRepository extends JpaRepository<Moderador,String> {

    List<Moderador> findByLogin_Username(String username);

    List<Moderador> findByLogin_Email(String email);

    List<Moderador> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerCodigo();
}
