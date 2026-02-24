package com.app_red_social.backend.application.service;

import com.app_red_social.backend.application.dto.moderador.ModeradorRequest;
import com.app_red_social.backend.domain.constant.Roles;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ConflictException;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.model.Moderador;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.repository.ModeradorRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.LoginUseCase;
import com.app_red_social.backend.domain.port.usecases.ModeradorUserCase;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeradorService  implements ModeradorUserCase {

    private  final ModeradorRepositoryPort moderadorRepositoryPort;
    private final LoginRepositoryPort loginRepositoryPort;
    private final LoginUseCase loginUseCase;

    @Override
    public Moderador listarModeradorCodigo(String codigo) {
        return moderadorRepositoryPort.listarModeradorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    @Override
    public Moderador listarUsername(String username) {
        return moderadorRepositoryPort.listarUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    @Override
    public Moderador listarEmail(String email) {
        return moderadorRepositoryPort.listarEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    @Override
    public Moderador listarTelefono(String telefono) {
        return moderadorRepositoryPort.listarTelefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    @Override
    public List<Moderador> listarNombre(String nombre) {
        return moderadorRepositoryPort.listarNombre(nombre);
    }

    @Override
    public List<Moderador> listarApellido(String apellido) {
        return moderadorRepositoryPort.listarApellido(apellido);
    }

    @Override
    public List<Moderador> listarNombreAndApellido(String nombre, String apellido) {
        return moderadorRepositoryPort.listarNombreAndApellido(nombre,apellido);
    }

    @Override
    public List<Moderador> listarGenero(String genero) {
        return moderadorRepositoryPort.listarGenero(genero);
    }

    @Override
    public List<Moderador> listarEdad(Integer edad) {
        return moderadorRepositoryPort.listarEdad(edad);
    }

    @Override
    public List<Moderador> listarEdadBetween(Integer edadMin, Integer edadMax) {
        return moderadorRepositoryPort.listarEdadBetween(edadMin,edadMax);
    }

    @Override
    public List<Moderador> listar() {
        return moderadorRepositoryPort.listar();
    }

    @Override
    public Moderador registrar(ModeradorRequest moderadorRequest) {
        String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginRepositoryPort.ultimoCodigo());
        String nuevoCodigoModerador = Secuencia.generarSiguienteCodigo(moderadorRepositoryPort.ultimoCodigo());

        Login nuevoLogin = loginUseCase.registrar(
                nuevoCodigoLogin,
                moderadorRequest.getUsername(),
                moderadorRequest.getEmail(),
                moderadorRequest.getTelefono(),
                moderadorRequest.getPassword(),
                Roles.ROLE_MODERADOR
        );

        LocalDate fechaNacimiento = moderadorRequest.getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        Moderador moderador = Moderador.builder()
                .codigo(nuevoCodigoModerador)
                .nombre(moderadorRequest.getNombre())
                .apellido(moderadorRequest.getApellido())
                .fechaNacimiento(fechaNacimiento)
                .edad(edad)
                .nacionalidad(moderadorRequest.getNacionalidad())
                .genero(moderadorRequest.getGenero())
                .login(nuevoLogin)
                .build();

        return moderadorRepositoryPort.registrar(moderador);
    }

    @Override
    public Moderador actualizar(String codigo,ModeradorRequest moderadorRequest) {

        Moderador moderador = moderadorRepositoryPort.listarModeradorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        Login loginActualizado = loginUseCase.actualizar(
                moderador.getLogin().getCodigo(),
                moderadorRequest.getUsername(),
                moderadorRequest.getEmail(),
                moderadorRequest.getTelefono(),
                moderadorRequest.getPassword()
        );

        moderador.setNombre(moderadorRequest.getNombre());
        moderador.setApellido(moderadorRequest.getApellido());
        moderador.setNacionalidad(moderadorRequest.getNacionalidad());
        moderador.setGenero(moderadorRequest.getGenero());
        moderador.setLogin(loginActualizado);

        if (moderadorRequest.getFechaNacimiento() != null) {
            LocalDate fechaNacimiento = moderadorRequest.getFechaNacimiento();
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

            moderador.setFechaNacimiento(fechaNacimiento);
            moderador.setEdad(edad);
        }

        return moderadorRepositoryPort.registrar(moderador);
    }
}
