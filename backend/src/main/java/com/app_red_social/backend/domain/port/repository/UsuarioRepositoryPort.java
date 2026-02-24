package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.application.dto.usuario.RoleUserStatsResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioListaResponse;
import com.app_red_social.backend.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    String ultimoCodigo();

    Optional<Usuario> buscarPorCodigo(String codigo);

    Usuario save(Usuario usuario);

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorUsername(String username);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorTelefono(String telefono);

    List<Usuario> buscarPorNombre(String nombre);

    List<Usuario> buscarPorApellido(String apellido);

    List<Usuario> buscarPorNombreYApellido(String nombre, String apellido);

    List<Usuario> buscarPorVerificado(Boolean verificado);

    List<Usuario> buscarPorCuentaPrivada(Boolean cuentaPrivada);

    List<Usuario> buscarPorCiudad(String ciudad);

    List<Usuario> buscarPorPais(String pais);

    List<Usuario> buscarPorGenero(String genero);

    List<Usuario> buscarPorEdad(Integer edad);

    List<Usuario> buscarPorRangoEdad(Integer edadMin, Integer edadMax);

    List<Usuario> buscarPorProveedor(String proveedor);

    List<UsuarioListaResponse> listarUsuarios(Integer option, String username, String estado);

    List<RoleUserStatsResponse> listarPorcentajeTiempo(int opcion);

    List<UsuarioActualResponse> obtenerUsuarioActual(Integer opcion, String codigo);

}
