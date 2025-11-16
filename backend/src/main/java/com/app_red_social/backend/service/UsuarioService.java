package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.dto.request.UsuarioRequest;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.repository.UsuarioRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;

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

    public String ultimoCodigo() {
        return usuarioRepository.obtenerCodigo();
    }

}
