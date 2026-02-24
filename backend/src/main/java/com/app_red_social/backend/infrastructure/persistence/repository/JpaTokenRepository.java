package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByToken(String token);

    List<TokenEntity> findByValido(String valido);

    @Query(value = "SELECT MAX(tk_codigo) FROM Token", nativeQuery = true)
    String obtenerCodigo();
/*
    @Modifying
    @Query("DELETE FROM TokenEntity t WHERE t.valido= INACTIVO")
    void deleteInactiveTokens();*/

}
