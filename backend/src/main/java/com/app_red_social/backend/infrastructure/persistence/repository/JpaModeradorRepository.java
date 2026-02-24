package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.ModeradorEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaModeradorRepository extends JpaRepository<ModeradorEntity,String> {

    Optional<ModeradorEntity> findByLogin_Username(String username);

    Optional<ModeradorEntity> findByLogin_Email(String email);

    Optional<ModeradorEntity> findByLogin_Telefono(String telefono);

    List<ModeradorEntity> findByNombre(String nombre);

    List<ModeradorEntity> findByApellido(String apellido);

    List<ModeradorEntity> findByNombreAndApellido(String nombre, String apellido);

    List<ModeradorEntity> findByGenero(String genero);

    List<ModeradorEntity> findByEdad(Integer edad);

    List<ModeradorEntity> findByEdadBetween(Integer edadMin, Integer edadMax);

    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerCodigo();

    @Query(value = "CALL sp_estadisticas_moderador(:opcion)", nativeQuery = true)
    List<Object[]> estadisticasModerador(@Param("opcion") Integer opcion);
}
