package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.AuthConstants;
import com.red_social.auth_service.dto.request.LoginRequest;
import com.red_social.auth_service.dto.response.TokenResponse;
import com.red_social.auth_service.exception.BadRequestException;
import com.red_social.auth_service.exception.JwtAuthenticationException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.repository.LoginRepository;
import com.red_social.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

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
                        .orElseThrow(() -> new UsernameNotFoundException(AuthConstants.USUARIO_NO_VALIDO + principal.getName())));
        return login;
    }


    public TokenResponse login(LoginRequest loginRequest) {
        String identificador = loginRequest.getLogin();
        String password = loginRequest.getPassword();

        try {
            // Autenticación con Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identificador, password)
            );

            // Buscar usuario por username, email o teléfono
            Login usuario = loginRepository.findByUsername(identificador)
                    .orElseGet(() -> loginRepository.findByEmail(identificador)
                            .orElseGet(() -> loginRepository.findByTelefono(identificador)
                                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + identificador))
                            )
                    );

            // 🔹 Generar token
            String token = jwtUtils.generateToken(usuario);
            tokenService.createToken(token);

            return new TokenResponse(token);

        } catch (BadCredentialsException ex) {
            // Usuario o contraseña incorrecta → 401
            throw new JwtAuthenticationException(AuthConstants.USER_PASS_INCORRECTO);
        } catch (Exception ex) {
            // Error genérico en login → 400 o 500 según contexto
            throw new BadRequestException(AuthConstants.ERROR_LOGIN);
        }
    }
}
