package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.domain.model.Login;

import java.util.Optional;


public interface LoginRepositoryPort {

    String ultimoCodigo();

    Optional<Login> findByCodigo(String username);

    Optional<Login> findByUsername(String username);

    Optional<Login> findByEmail(String email);

    Optional<Login> findByTelefono(String telefono);/**/

    Login save(Login login);

}
