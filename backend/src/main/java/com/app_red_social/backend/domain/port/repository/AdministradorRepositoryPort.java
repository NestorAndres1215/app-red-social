package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.domain.model.Administrador;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepositoryPort {

    String ultimoCodigo();

    Optional<Administrador> listarModeradorCodigo(String codigo);

    Optional<Administrador> listarUsername(String username);

    Optional<Administrador> listarEmail(String email);

    Optional<Administrador> listarTelefono(String telefono);

    List<Administrador> listarNombre(String nombre);

    List<Administrador> listarApellido(String apellido);

    List<Administrador> listarNombreAndApellido(String nombre, String apellido);

    List<Administrador> listarGenero(String genero);

    List<Administrador> listarEdad(Integer edad);

    List<Administrador> listarEdadBetween(Integer edadMin, Integer edadMax);

    List<Administrador> listar();

    List<AdministradorActualResponse> obtenerPorLogin(String loginCodigo);

    Administrador registrar(Administrador administrador);
}
