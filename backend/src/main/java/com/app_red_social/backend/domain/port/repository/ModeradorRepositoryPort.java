package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.application.dto.moderador.ModeradorRequest;
import com.app_red_social.backend.domain.model.Moderador;

import java.util.List;
import java.util.Optional;

public interface ModeradorRepositoryPort {

    String ultimoCodigo();


    Optional<Moderador> listarModeradorCodigo(String codigo);

    Optional<Moderador> listarUsername(String username);

    Optional<Moderador> listarEmail(String email);

    Optional<Moderador> listarTelefono(String telefono);

    List<Moderador> listarNombre(String nombre);

    List<Moderador> listarApellido(String apellido);

    List<Moderador> listarNombreAndApellido(String nombre, String apellido);

    List<Moderador> listarGenero(String genero);

    List<Moderador> listarEdad(Integer edad);

    List<Moderador> listarEdadBetween(Integer edadMin, Integer edadMax);

    List<Moderador> listar();

    Moderador registrar(Moderador moderador);
}
