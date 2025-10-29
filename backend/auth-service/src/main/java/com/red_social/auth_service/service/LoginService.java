package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.EstadoConstants;
import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.EstadoUsuario;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.model.Rol;
import com.red_social.auth_service.repository.EstadoUsuarioRepository;
import com.red_social.auth_service.repository.LoginRepository;
import com.red_social.auth_service.repository.RolRepository;
import com.red_social.auth_service.repository.TokenRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final RolRepository rolRepository;
    private final EstadoUsuarioRepository estadoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    public String ultimoCodigo() {
        return loginRepository.obtenerCodigo();
    }

    public Optional<Login> listarId(String id) {
        return loginRepository.findById(id);
    }

    public Optional<Login> listarUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public Optional<Login> listarEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public Optional<Login> listarTelefono(String telefono) {
        return loginRepository.findByTelefono(telefono);
    }

    public List<Login> listar() {
        return loginRepository.findAll();
    }

    public Login registrar(String codigo, String username, String email, String telefono, String password, String rol) {


        Rol roles = rolRepository.findByNombre(rol)
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found"));

        EstadoUsuario estadoUsuario = estadoUsuarioRepository.findByNombre(EstadoConstants.ACTIVO)
                .orElseThrow(() -> new ResourceNotFoundException("Default estado de usuario not found"));

        String passwordEncriptada = passwordEncoder.encode(password);
        Login login = Login.builder()
                .codigo(codigo)
                .username(username)
                .email(email)
                .telefono(telefono)
                .password(passwordEncriptada)
                .fechaCreacion(LocalDateTime.now())
                .rol(roles)
                .estadoUsuario(estadoUsuario)
                .build();


        return loginRepository.save(login);
    }

    private void validarDuplicados(String username, String email, String telefono) {

        // ✅ El email siempre debe validarse porque es obligatorio
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        if (loginRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException("Ya existe un usuario con el correo: " + email);
        }

        // ✅ Username es opcional → solo validar si tiene valor
        if (username != null && !username.isBlank() && loginRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException("Ya existe un usuario con el nombre de usuario: " + username);
        }

        // ✅ Teléfono es opcional → solo validar si tiene valor
        if (telefono != null && !telefono.isBlank() && loginRepository.existsByTelefono(telefono)) {
            throw new ResourceAlreadyExistsException("Ya existe un usuario con el teléfono: " + telefono);
        }
    }

}
