package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByLogin_Username(String username);

    List<Usuario> findByLogin_Email(String email);

    List<Usuario> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();
}
