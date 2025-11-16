package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin_Username(String username);

    Optional<Usuario> findByLogin_Email(String email);

    Optional<Usuario> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();

}
