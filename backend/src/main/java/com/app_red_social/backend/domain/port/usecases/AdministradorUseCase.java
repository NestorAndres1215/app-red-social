package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.application.dto.administrador.AdministradorRequest;
import com.app_red_social.backend.domain.model.Administrador;
import java.util.List;

public interface AdministradorUseCase {

    Administrador listarAdministradorCodigo(String codigo);

    Administrador listarUsername(String username);

    Administrador listarEmail(String email);

    Administrador listarTelefono(String telefono);

    List<Administrador> listarNombre(String nombre);

    List<Administrador> listarApellido(String apellido);

    List<Administrador> listarNombreAndApellido(String nombre, String apellido);

    List<Administrador> listarGenero(String genero);

    List<Administrador> listarEdad(Integer edad);

    List<Administrador> listarEdadBetween(Integer edadMin, Integer edadMax);

    List<Administrador> listar();

    List<AdministradorActualResponse> obtenerPorLogin(String loginCodigo);

    Administrador registrar(AdministradorRequest administradorRequest);

    Administrador actualizar(String codigo, AdministradorRequest administradorRequest);
}
