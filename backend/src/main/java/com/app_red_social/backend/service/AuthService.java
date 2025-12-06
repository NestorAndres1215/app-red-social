package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.LoginRequest;
import com.app_red_social.backend.dto.response.TokenResponse;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.LoginRepository;
import com.app_red_social.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final LoginRepository loginRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtUtil jwtUtils;

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

}
