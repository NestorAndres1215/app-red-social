package com.app_red_social.backend.application.service;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.application.dto.administrador.AdministradorRequest;
import com.app_red_social.backend.domain.constant.Roles;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.Administrador;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.model.Moderador;
import com.app_red_social.backend.domain.port.repository.AdministradorRepositoryPort;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.AdministradorUseCase;
import com.app_red_social.backend.domain.port.usecases.LoginUseCase;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AdministradorService implements AdministradorUseCase {

    private  final AdministradorRepositoryPort administradorRepositoryPort;
    private final LoginRepositoryPort loginRepositoryPort;
    private final LoginUseCase loginUseCase;

    @Override
    public Administrador listarAdministradorCodigo(String codigo) {
        return administradorRepositoryPort.listarModeradorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    @Override
    public Administrador listarUsername(String username) {
        return administradorRepositoryPort.listarUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    @Override
    public Administrador listarEmail(String email) {
        return administradorRepositoryPort.listarEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    @Override
    public Administrador listarTelefono(String telefono) {
        return administradorRepositoryPort.listarTelefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    @Override
    public List<Administrador> listarNombre(String nombre) {
        return administradorRepositoryPort.listarNombre(nombre);
    }

    @Override
    public List<Administrador> listarApellido(String apellido) {
        return administradorRepositoryPort.listarApellido(apellido);
    }

    @Override
    public List<Administrador> listarNombreAndApellido(String nombre, String apellido) {
        return administradorRepositoryPort.listarNombreAndApellido(nombre, apellido);
    }

    @Override
    public List<Administrador> listarGenero(String genero) {
        return administradorRepositoryPort.listarGenero(genero);
    }

    @Override
    public List<Administrador> listarEdad(Integer edad) {
        return administradorRepositoryPort.listarEdad(edad);
    }

    @Override
    public List<Administrador> listarEdadBetween(Integer edadMin, Integer edadMax) {
        return administradorRepositoryPort.listarEdadBetween(edadMin,edadMax);
    }

    @Override
    public List<Administrador> listar() {
        return administradorRepositoryPort.listar();
    }

    @Override
    public List<AdministradorActualResponse> obtenerPorLogin(String loginCodigo) {
        return administradorRepositoryPort.obtenerPorLogin(loginCodigo);
    }

    @Override
    public Administrador registrar(AdministradorRequest administradorRequest) {

        String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginRepositoryPort.ultimoCodigo());
        String nuevoCodigoModerador = Secuencia.generarSiguienteCodigo(administradorRepositoryPort.ultimoCodigo());

        Login nuevoLogin = loginUseCase.registrar(
                nuevoCodigoLogin,
                administradorRequest.getUsername(),
                administradorRequest.getEmail(),
                administradorRequest.getTelefono(),
                administradorRequest.getPassword(),
                Roles.ROLE_ADMIN
        );

        LocalDate fechaNacimiento = administradorRequest.getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        Administrador administrador = Administrador.builder()
                .codigo(nuevoCodigoModerador)
                .nombre(administradorRequest.getNombre())
                .apellido(administradorRequest.getApellido())
                .fechaNacimiento(fechaNacimiento)
                .edad(edad)
                .nacionalidad(administradorRequest.getNacionalidad())
                .genero(administradorRequest.getGenero())
                .login(nuevoLogin)
                .build();

        return administradorRepositoryPort.registrar(administrador);
    }

    @Override
    public Administrador actualizar(String codigo, AdministradorRequest administradorRequest) {

        Administrador administrador = administradorRepositoryPort.listarModeradorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        Login loginActualizado = loginUseCase.actualizar(
                administrador.getLogin().getCodigo(),
                administradorRequest.getUsername(),
                administradorRequest.getEmail(),
                administradorRequest.getTelefono(),
                administradorRequest.getPassword()
        );

        administrador.setNombre(administradorRequest.getNombre());
        administrador.setApellido(administradorRequest.getApellido());
        administrador.setNacionalidad(administradorRequest.getNacionalidad());
        administrador.setGenero(administradorRequest.getGenero());
        administrador.setLogin(loginActualizado);

        if (administradorRequest.getFechaNacimiento() != null) {
            LocalDate fechaNacimiento = administradorRequest.getFechaNacimiento();
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

            administrador.setFechaNacimiento(fechaNacimiento);
            administrador.setEdad(edad);
        }

        return administradorRepositoryPort.registrar(administrador);
    }
}
