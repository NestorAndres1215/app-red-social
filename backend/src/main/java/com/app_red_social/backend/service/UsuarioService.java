package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Auth;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.dto.request.UsuarioRequest;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.repository.UsuarioRepository;
import com.app_red_social.backend.util.Secuencia;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
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
            Login login = loginService.listarCodigo(usuarioRequest.getEmail());
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

}
