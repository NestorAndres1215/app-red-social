package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.constant.Estados;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ConflictException;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.EstadoUsuario;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.repository.EstadoRepositoryPort;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.repository.RolRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.LoginUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final LoginRepositoryPort loginRepositoryPort;
    private final EstadoRepositoryPort estadoRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Login actualizar(String codigo, String username, String email, String telefono, String password) {
        Login login = loginRepositoryPort.findByCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
        validarDuplicadosEnActualizacion(login, username, email, telefono);

        login.setUsername(username);
        login.setEmail(email);
        login.setTelefono(telefono);

        if (password != null && !password.isBlank()) {
            login.setPassword(passwordEncoder.encode(password));
        }

        return loginRepositoryPort.save(login);
    }

    @Override
    public Login registrar(String codigo, String username, String email, String telefono, String password, String rol) {

        validarDuplicados(username, email, telefono);

        Rol roles = rolRepositoryPort.buscarPorNombre(rol)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ROL_NO_ENCONTRADO));

        EstadoUsuario estadoUsuario = estadoRepositoryPort.buscarPorNombre(Estados.ACTIVO)
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

        return loginRepositoryPort.save(login);
    }

    private void validarDuplicadosEnActualizacion(Login login, String username, String email, String telefono) {

        loginRepositoryPort.findByUsername(username)
                .filter(l -> !l.getCodigo().equals(login.getCodigo()))
                .ifPresent(l -> {
                    throw new ConflictException("El username ya existe");
                });

        loginRepositoryPort.findByEmail(email)
                .filter(l -> !l.getCodigo().equals(login.getCodigo()))
                .ifPresent(l -> {
                    throw new ConflictException("El email ya está registrado");
                });

        loginRepositoryPort.findByTelefono(telefono)
                .filter(l -> !l.getCodigo().equals(login.getCodigo()))
                .ifPresent(l -> {
                    throw new ConflictException("El teléfono ya está registrado");
                });
    }

    private void validarDuplicados(String username, String email, String telefono) {

        if (loginRepositoryPort.findByUsername(username).isPresent()) {
            throw new ConflictException("El username ya existe");
        }

        if (loginRepositoryPort.findByEmail(email).isPresent()) {
            throw new ConflictException("El email ya está registrado");
        }

        if (loginRepositoryPort.findByTelefono(telefono).isPresent()) {
            throw new ConflictException("El teléfono ya está registrado");
        }
    }

    @Override
    public Optional<Login> buscarPorCodigo(String codigo) {
        return loginRepositoryPort.findByCodigo(codigo);
    }

    @Override
    public Login inactivar(String codigo) {
        return cambiarEstado(codigo, Estados.INACTIVO);
    }

    @Override
    public Login suspender(String codigo) {
        return cambiarEstado(codigo, Estados.SUSPENDIDO);
    }

    @Override
    public Login activar(String codigo) {
        return cambiarEstado(codigo, Estados.ACTIVO);
    }

    @Override
    public Login bloquear(String codigo) {
        return cambiarEstado(codigo, Estados.BLOQUEADO);
    }

    @Override
    public Login eliminado(String codigo) {
        return cambiarEstado(codigo, Estados.ELIMINADO);
    }

    @Override
    public Login inhabilitado(String codigo) {
        return cambiarEstado(codigo, Estados.INHABILITADO);
    }

    @Override
    public Login cambiarEstado(String codigo, String nombreEstado) {

        Login entity = loginRepositoryPort.findByCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO)
                );

        EstadoUsuario estado = estadoRepositoryPort.buscarPorNombre(nombreEstado)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO)
                );

        entity.setEstadoUsuario(estado);

        return loginRepositoryPort.save(entity);
    }

    @Override
    public Login ultimoLogueo(String username) {

        Login entity = loginRepositoryPort.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO)
                );

        entity.setUltimoLogin(LocalDateTime.now());
        return loginRepositoryPort.save(entity);
    }
}
