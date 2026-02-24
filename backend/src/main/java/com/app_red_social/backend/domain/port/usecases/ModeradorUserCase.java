package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.application.dto.moderador.ModeradorRequest;
import com.app_red_social.backend.domain.model.Moderador;

import java.util.List;

public interface ModeradorUserCase {

    Moderador listarModeradorCodigo(String codigo);

    Moderador listarUsername(String username);

    Moderador listarEmail(String email);

    Moderador listarTelefono(String telefono);

    List<Moderador> listarNombre(String nombre);

    List<Moderador> listarApellido(String apellido);

    List<Moderador> listarNombreAndApellido(String nombre, String apellido);

    List<Moderador> listarGenero(String genero);

    List<Moderador> listarEdad(Integer edad);

    List<Moderador> listarEdadBetween(Integer edadMin, Integer edadMax);

    List<Moderador> listar();

    Moderador registrar(ModeradorRequest moderadorRequest);

    Moderador actualizar(String codigo,ModeradorRequest moderadorRequest);
}
