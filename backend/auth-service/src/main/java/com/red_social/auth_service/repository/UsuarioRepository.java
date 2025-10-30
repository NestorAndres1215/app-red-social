package com.red_social.auth_service.repository;


import com.red_social.auth_service.dto.response.UsuarioDTO;
import com.red_social.auth_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByLogin_Username(String username);

    Optional<Usuario> findByLogin_Email(String email);

    List<Usuario> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();

    @Query(value = "CALL Sp_listarUsuario(:opcion, :codigo)", nativeQuery = true)
    List<Object[]> spGetUsuarioPorCodigo(@Param("opcion") String opcion, @Param("codigo") String codigo);
}
