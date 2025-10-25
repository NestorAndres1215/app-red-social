package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Token;
import com.red_social.auth_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    // Buscar un token por su valor
    Optional<Token> findByToken(String token);

    List<Token> findByValido(String valido);

    @Query(value = "SELECT MAX(tk_codigo) FROM Token", nativeQuery = true)
    String obtenerCodigo();
}
