package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin_Username(String username);

    Optional<Usuario> findByLogin_Email(String email);

    Optional<Usuario> findByLogin_Telefono(String telefono);


    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();


    @Query(value = "CALL sp_listar_usuarios(:option, :username, :estado)", nativeQuery = true)
    List<Object[]> listarUsuarios(
            @Param("option") Integer option,
            @Param("username") String username,
            @Param("estado") String estado
    );




    @Query(value = "CALL Sp_UsuarioActual(:opcion, :codigo)", nativeQuery = true)
    List<Object[]> obtenerUsuarioActual(@Param("opcion") Integer opcion, @Param("codigo") String codigo);


    @Query(value = "CALL sp_listarPorcentajeTiempo(:opcion)", nativeQuery = true)
    List<Object[]> listarPorcentajeTiempo(@Param("opcion") int opcion);
}
