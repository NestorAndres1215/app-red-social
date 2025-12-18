package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.DuplicateErrorMessages;
import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.EstadoUsuario;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.repository.EstadoUsuarioRepository;
import com.app_red_social.backend.repository.LoginRepository;
import com.app_red_social.backend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final RolRepository rolRepository;
    private final EstadoUsuarioRepository estadoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public String ultimoCodigo() {
        return loginRepository.obtenerCodigo();
    }



    public Login listarCodigo(String codigo) {
        return loginRepository.findById(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Login listarUsername(String username) {
        return loginRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    public Login listarEmail(String email) {
        return loginRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    public Login listarTelefono(String telefono) {
        return loginRepository.findByTelefono(telefono)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    public Login registrar(String codigo, String username, String email, String telefono, String password, String rol) {

        validarDuplicados(username, email, telefono);

        Rol roles = rolRepository.findByNombre(rol)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ROL_NO_ENCONTRADO));

        EstadoUsuario estadoUsuario = estadoUsuarioRepository.findByNombre(Estados.ACTIVO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

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

        if (loginRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException(DuplicateErrorMessages.EMAIL_EXISTENTE);
        }

        if (username != null && !username.trim().isEmpty() && loginRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException(DuplicateErrorMessages.USERNAME_EXISTENTE);
        }

        if (telefono != null && !telefono.trim().isEmpty() && loginRepository.existsByTelefono(telefono)) {
            throw new ResourceAlreadyExistsException(DuplicateErrorMessages.TELEFONO_EXISTENTE);
        }

    }

    public Login actualizar(String codigo, String username, String email, String telefono, String password, String rol) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));


        if (username != null && !username.isBlank() && !username.equals(login.getUsername())) {
            if (loginRepository.existsByUsername(username)) {
                throw new ResourceAlreadyExistsException(DuplicateErrorMessages.USERNAME_EXISTENTE);
            }
            login.setUsername(username);
        }

        if (email != null && !email.isBlank() && !email.equals(login.getEmail())) {
            if (loginRepository.existsByEmail(email)) {
                throw new ResourceAlreadyExistsException(DuplicateErrorMessages.EMAIL_EXISTENTE);
            }
            login.setEmail(email);
        }

        if (telefono != null && !telefono.isBlank() && !telefono.equals(login.getTelefono())) {
            if (loginRepository.existsByTelefono(telefono)) {
                throw new ResourceAlreadyExistsException(DuplicateErrorMessages.TELEFONO_EXISTENTE);
            }
            login.setTelefono(telefono);
        }

        if (rol != null && !rol.isBlank()) {
            Rol nuevoRol = rolRepository.findByNombre(rol)
                    .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ROL_NO_ENCONTRADO));

            login.setRol(nuevoRol);
        }

        if (password != null && !password.isBlank()) {
            login.setPassword(passwordEncoder.encode(password));
        }

        return loginRepository.save(login);
    }

    public Login inactivar(String codigo) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoInactivo = estadoUsuarioRepository.findByNombre(Estados.INACTIVO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoInactivo);
        return loginRepository.save(login);

    }

    public Login suspender(String codigo) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoSuspendido = estadoUsuarioRepository.findByNombre(Estados.SUSPENDIDO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoSuspendido);
        return loginRepository.save(login);

    }

    public Login activar(String codigo) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoActivo = estadoUsuarioRepository.findByNombre(Estados.ACTIVO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoActivo);
        return loginRepository.save(login);

    }

    public Login bloquear(String codigo) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoBloqueado = estadoUsuarioRepository.findByNombre(Estados.BLOQUEADO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoBloqueado);
        return loginRepository.save(login);

    }

    public Login eliminado(String codigo) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        EstadoUsuario estadoEliminado = estadoUsuarioRepository.findByNombre(Estados.ELIMINADO)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));

        login.setEstadoUsuario(estadoEliminado);
        return loginRepository.save(login);

    }



}
