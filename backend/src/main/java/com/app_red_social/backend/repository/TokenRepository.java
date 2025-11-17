package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Token;
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