package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Auth;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.dto.request.UsuarioRequest;
import com.app_red_social.backend.dto.response.UsuarioActualResponse;
import com.app_red_social.backend.dto.response.UsuarioListaResponse;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.mapper.UsuarioActualMapper;
import com.app_red_social.backend.mapper.UsuarioMapper;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.repository.UsuarioRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioActualMapper usuarioActualMapper;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario listarCodigo(String codigo) {
        return usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Usuario listarUsername(String username) {
        return usuarioRepository.findByLogin_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    public Usuario listarEmail(String email) {
        return usuarioRepository.findByLogin_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    public Usuario listarTelefono(String telefono) {
        return usuarioRepository.findByLogin_Telefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    public Usuario saveOrUpdateGoogleUser(UsuarioRequest usuarioRequest) {

        return usuarioRepository.findByLogin_Email(usuarioRequest.getEmail()).map(login -> {
            login.setNombre(usuarioRequest.getNombre());
            login.setApellido(usuarioRequest.getApellido());
            login.setPhotoUrl(usuarioRequest.getPhotoUrl());
            return usuarioRepository.save(login);
        }).orElseGet(() -> {

            final String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginService.ultimoCodigo());
            loginService.registrar(nuevoCodigoLogin, "", usuarioRequest.getEmail(), "", "", Roles.ROLE_USER);
            Login login = loginService.listarEmail(usuarioRequest.getEmail());
            final String nuevoCodigoUsuario = Secuencia.generarSiguienteCodigo(ultimoCodigo());

            Usuario usuario = Usuario.builder()
                    .codigo(nuevoCodigoUsuario)
                    .nombre(usuarioRequest.getNombre())
                    .apellido(usuarioRequest.getApellido())
                    .photoUrl(usuarioRequest.getPhotoUrl())
                    .provider(usuarioRequest.getProvider())
                    .login(login)
                    .build();

            return usuarioRepository.save(usuario);
        });
    }

    public Usuario registrar(RegisterRequest registerRequest) {

        final String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginService.ultimoCodigo());
        loginService.registrar(nuevoCodigoLogin, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getTelefono(), registerRequest.getPassword(), Roles.ROLE_USER);
        Login login = loginService.listarCodigo(nuevoCodigoLogin);
        final String nuevoCodigoUsuario = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        Usuario usuario = Usuario.builder()
                .codigo(nuevoCodigoUsuario)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .provider(Auth.LOCAL)
                .genero(registerRequest.getGenero())
                .nacionalidad(registerRequest.getNacionalidad())
                .login(login)
                .build();
        return usuarioRepository.save(usuario);
    }

    public String ultimoCodigo() {
        return usuarioRepository.obtenerCodigo();
    }

    public List<UsuarioListaResponse> listarUsuarios(Integer option, String username, String estado) {

        usuarioRepository.findByLogin_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));

        List<Object[]> result = usuarioRepository.listarUsuarios(option, username, estado);
        return usuarioMapper.toDtoList(result);
    }

    public List<UsuarioActualResponse> obtenerUsuario( String codigo) {

        usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        List<Object[]> result = usuarioRepository.obtenerUsuarioActual(1, codigo);

        return usuarioActualMapper.toList(result);
    }
}
