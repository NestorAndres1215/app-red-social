package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaAdministradorRepository extends JpaRepository<AdministradorEntity,String> {


    Optional<AdministradorEntity> findByLogin_Username(String username);

    Optional<AdministradorEntity> findByLogin_Email(String email);

    Optional<AdministradorEntity> findByLogin_Telefono(String telefono);

    Optional<AdministradorEntity> findByLogin_Codigo(String usuarioCodigo);

    List<AdministradorEntity> findByNombre(String nombre);

    List<AdministradorEntity> findByApellido(String apellido);

    List<AdministradorEntity> findByNombreAndApellido(String nombre, String apellido);

    List<AdministradorEntity> findByGenero(String genero);

    List<AdministradorEntity> findByEdad(Integer edad);

    List<AdministradorEntity> findByEdadBetween(Integer edadMin, Integer edadMax);


    @Query(value = "SELECT MAX(ad_codigo) FROM Administrador", nativeQuery = true)
    String obtenerCodigo();

    @Query(value = "CALL sp_actualAdministrador(:loginCodigo)", nativeQuery = true)
    List<Object[]> obtenerAdministradorPorLogin(@Param("loginCodigo") String loginCodigo);
}
