package com.app_red_social.backend.infrastructure.persistence.repository;


import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, String> {


    @Query(value = "CALL Sp_UsuarioActual(:opcion, :codigo)", nativeQuery = true)
    List<Object[]> obtenerUsuarioActual(@Param("opcion") Integer opcion, @Param("codigo") String codigo);


    @Query(value = "CALL sp_listarPorcentajeTiempo(:opcion)", nativeQuery = true)
    List<Object[]> listarPorcentajeTiempo(@Param("opcion") int opcion);

    @Query(value = "CALL sp_listar_usuarios(:option, :username, :estado)", nativeQuery = true)
    List<Object[]> listarUsuarios(
            @Param("option") Integer option,
            @Param("username") String username,
            @Param("estado") String estado
    );

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();

    Optional<UsuarioEntity> findByLogin_Username(String username);

    Optional<UsuarioEntity> findByLogin_Email(String email);

    Optional<UsuarioEntity> findByLogin_Telefono(String telefono);

    List<UsuarioEntity> findByNombre(String nombre);

    List<UsuarioEntity> findByApellido(String apellido);

    List<UsuarioEntity> findByNombreAndApellido(String nombre, String apellido);

    List<UsuarioEntity> findByVerificado(String verificado);

    List<UsuarioEntity> findByCuentaPrivada(Boolean cuentaPrivada);

    List<UsuarioEntity> findByCiudad(String ciudad);

    List<UsuarioEntity> findByPais(String pais);

    List<UsuarioEntity> findByGenero(String genero);

    List<UsuarioEntity> findByEdad(Integer edad);

    List<UsuarioEntity> findByEdadBetween(Integer edadMin, Integer edadMax);

    List<UsuarioEntity> findByProvider(String provider);
}