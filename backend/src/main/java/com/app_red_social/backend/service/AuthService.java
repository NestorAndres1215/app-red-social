package com.app_red_social.backend.service;


import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.constants.messages.GlobalErrorMessages;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.ContrasenaRequest;
import com.app_red_social.backend.dto.request.LoginRequest;
import com.app_red_social.backend.dto.response.TokenResponse;
import com.app_red_social.backend.exception.BadRequestException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.EstadoUsuario;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.EstadoUsuarioRepository;
import com.app_red_social.backend.repository.LoginRepository;
import com.app_red_social.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final LoginRepository loginRepository;
    private final AuthenticationManager authenticationManager;
    private final EstadoUsuarioRepository estadoUsuarioRepository;
    private final TokenService tokenService;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public Login actualUsuario(Principal principal) {
        Login login = loginRepository.findByUsername(principal.getName())
                .orElseGet(() -> loginRepository.findByEmail(principal.getName())
                        .orElseThrow(() -> new UsernameNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO)));
        return login;
    }

    public TokenResponse login(LoginRequest loginRequest) {
        String identificador = loginRequest.getLogin();
        String password = loginRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identificador, password));

        Login usuario = loginRepository.findByUsername(identificador)
                .orElseGet(() -> loginRepository.findByEmail(identificador)
                        .orElseGet(() -> loginRepository.findByTelefono(identificador)
                                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO))
                        )
                );
        String token = jwtUtils.generateToken(usuario);
        tokenService.createToken(token);
        return new TokenResponse(token);
    }


    public List<Login> listarModeradores() {
        return loginRepository.findByRolNombre(Roles.ROLE_MODERADOR);
    }
    public List<Login> listarLogin() {
        return loginRepository.findAll();
    }



    public Login ultimoLogueo(String username) {

        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));

        login.setUltimoLogin(LocalDateTime.now());
        return loginRepository.save(login);
    }

    public Login cambiarContrasena(ContrasenaRequest contrasenaRequest) {

        if (!contrasenaRequest.getNuevaContrasena()
                .equals(contrasenaRequest.getConfirmarContrasena())) {
            throw new BadRequestException(GlobalErrorMessages.PASSWORD_NO_COINCIDE);
        }

        Login login = loginRepository.findByUsername(contrasenaRequest.getUsuario())
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));

        if (passwordEncoder.matches(contrasenaRequest.getNuevaContrasena(), login.getPassword())) {
            throw new BadRequestException(GlobalErrorMessages.PASSWORD_IGUAL_ANTERIOR);
        }

        String passwordEncriptada = passwordEncoder.encode(contrasenaRequest.getNuevaContrasena());
        login.setPassword(passwordEncriptada);

        return loginRepository.save(login);
    }

    public Login bloquear(String username) {

        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoBloqueado = estadoUsuarioRepository.findByNombre(Estados.BLOQUEADO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoBloqueado);
        return loginRepository.save(login);

    }

}
