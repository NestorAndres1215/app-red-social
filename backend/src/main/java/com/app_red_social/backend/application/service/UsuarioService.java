package com.app_red_social.backend.application.service;

import com.app_red_social.backend.application.dto.usuario.RoleUserStatsResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioListaResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioRequest;
import com.app_red_social.backend.domain.constant.Roles;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ConflictException;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.repository.UsuarioRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.LoginUseCase;
import com.app_red_social.backend.domain.port.usecases.UsuarioUseCase;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final LoginRepositoryPort loginRepository;
    private final LoginUseCase loginUseCase;

    @Override
    public Usuario actualizar(String codigo, UsuarioRequest request) {

        Usuario usuarioExistente = usuarioRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuarioExistente.setNombre(request.getNombre());
        usuarioExistente.setApellido(request.getApellido());
        usuarioExistente.setPerfil(request.getPerfil());
        usuarioExistente.setFechaNacimiento(request.getFechaNacimiento());
        LocalDate fechaNacimiento = request.getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        usuarioExistente.setEdad(edad);
        usuarioExistente.setNacionalidad(request.getNacionalidad());
        usuarioExistente.setPais(request.getPais());
        usuarioExistente.setProvider(request.getProvider());
        usuarioExistente.setGenero(request.getGenero());
        usuarioExistente.setCiudad(request.getCiudad());
        usuarioExistente.setPresentacion(request.getPresentacion());
        usuarioExistente.setCuentaPrivada(request.getCuentaPrivada());

        Login login = usuarioExistente.getLogin();
        login.setEmail(request.getEmail());
        login.setTelefono(request.getTelefono());
        login.setUsername(request.getUsername());

        loginRepository.save(login);

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public Usuario registrar(UsuarioRequest request) {
        String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginRepository.ultimoCodigo());
         String nuevoCodigoUsuario = Secuencia.generarSiguienteCodigo(usuarioRepository.ultimoCodigo());

        loginRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new ConflictException("El username ya existe");
                });

        Login nuevoLogin = loginUseCase.registrar(nuevoCodigoLogin, request.getUsername(), request.getEmail(), request.getTelefono(), request.getPassword(), Roles.ROLE_USER);


        LocalDate fechaNacimiento = request.getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        Usuario usuario = Usuario.builder()
                .codigo(nuevoCodigoUsuario)
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .perfil(request.getPerfil())
                .fechaNacimiento(request.getFechaNacimiento())
                .nacionalidad(request.getNacionalidad())
                .pais(request.getPais())
                .provider(request.getProvider())
                .genero(request.getGenero())
                .publicaciones(0)
                .seguidores(0)
                .seguidos(0)
                .edad(edad)
                .login(nuevoLogin)
                .cuentaPrivada(false)
                .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario saveOrUpdateGoogleUser(UsuarioRequest usuarioRequest) {
        return usuarioRepository.buscarPorEmail(usuarioRequest.getEmail()).map(login -> {
            login.setNombre(usuarioRequest.getNombre());
            login.setApellido(usuarioRequest.getApellido());
            login.setPerfil(usuarioRequest.getPerfil());
            return usuarioRepository.save(login);
        }).orElseGet(() -> {

            String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginRepository.ultimoCodigo());
            String nuevoCodigoUsuario = Secuencia.generarSiguienteCodigo(usuarioRepository.ultimoCodigo());

            Login nuevoLogin = loginUseCase.registrar(nuevoCodigoLogin, "", usuarioRequest.getEmail(), "", "", Roles.ROLE_USER);

            Usuario usuario = Usuario.builder()
                    .codigo(nuevoCodigoUsuario)
                    .nombre(usuarioRequest.getNombre())
                    .apellido(usuarioRequest.getApellido())
                    .perfil(usuarioRequest.getPerfil())
                    .provider(usuarioRequest.getProvider())
                    .login(nuevoLogin)
                    .build();

            return usuarioRepository.save(usuario);
        });
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        return usuarioRepository.buscarPorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.buscarPorUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    @Override
    public Usuario buscarPorTelefono(String telefono) {
        return usuarioRepository.buscarPorTelefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        return usuarioRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Usuario> buscarPorNombreYApellido(String nombre, String apellido) {
        return usuarioRepository.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    public List<Usuario> buscarPorVerificado(Boolean verificado) {
        return usuarioRepository.buscarPorVerificado(verificado);
    }

    @Override
    public List<Usuario> buscarPorCuentaPrivada(Boolean cuentaPrivada) {
        return usuarioRepository.buscarPorCuentaPrivada(cuentaPrivada);
    }

    @Override
    public List<Usuario> buscarPorCiudad(String ciudad) {
        return usuarioRepository.buscarPorCiudad(ciudad);
    }

    @Override
    public List<Usuario> buscarPorPais(String pais) {
        return usuarioRepository.buscarPorPais(pais);
    }

    @Override
    public List<Usuario> buscarPorGenero(String genero) {
        return usuarioRepository.buscarPorGenero(genero);
    }

    @Override
    public List<Usuario> buscarPorEdad(Integer edad) {
        return usuarioRepository.buscarPorEdad(edad);
    }

    @Override
    public List<Usuario> buscarPorRangoEdad(Integer edadMin, Integer edadMax) {
        return usuarioRepository.buscarPorRangoEdad(edadMin, edadMax);
    }

    @Override
    public List<Usuario> buscarPorProveedor(String proveedor) {
        return usuarioRepository.buscarPorProveedor(proveedor);
    }

    @Override
    public List<UsuarioListaResponse> listarUsuarios(Integer option, String username, String estado) {
        return usuarioRepository.listarUsuarios(option,username,estado);
    }

    @Override
    public  List<RoleUserStatsResponse> listarPorcentajeTiempo(int opcion) {
        return  usuarioRepository.listarPorcentajeTiempo(opcion);
    }

    @Override
    public List<UsuarioActualResponse> obtenerUsuarioActual(String codigo) {
        return usuarioRepository.obtenerUsuarioActual(1, codigo);
    }
}
