package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModeradorRepository extends JpaRepository<Moderador, String> {

    Optional<Moderador> findByLogin_Username(String username);

    Optional<Moderador> findByLogin_Email(String email);

    Optional<Moderador> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerCodigo();

    @Query(value = "CALL sp_estadisticas_moderador(:opcion)", nativeQuery = true)
    List<Object[]> estadisticasModerador(@Param("opcion") Integer opcion);
}
