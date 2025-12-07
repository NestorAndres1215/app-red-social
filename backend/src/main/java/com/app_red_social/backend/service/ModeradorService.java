package com.app_red_social.backend.service;


import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.RegisterRequest;

import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.mapper.EstadisticasModeradorMapper;

import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Moderador;
import com.app_red_social.backend.repository.ModeradorRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeradorService {

    private final ModeradorRepository moderadorRepository;
    private final LoginService loginService;


    public Moderador listarModeradorCodigo(String codigo) {
        return moderadorRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Moderador listarUsername(String username) {
        return moderadorRepository.findByLogin_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    public Moderador listarEmail(String email) {
        return moderadorRepository.findByLogin_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    public Moderador listarTelefono(String telefono) {
        return moderadorRepository.findByLogin_Telefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    public List<Moderador> listar() {
        return moderadorRepository.findAll();
    }

    public Moderador registrar(RegisterRequest registerRequest) {

        final String nuevoCodigoLogin = Secuencia.generarSiguienteCodigo(loginService.ultimoCodigo());

        loginService.registrar(
                nuevoCodigoLogin,
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getTelefono(),
                registerRequest.getPassword(),
                Roles.ROLE_ADMIN
        );

        Login login = loginService.listarCodigo(nuevoCodigoLogin);
        final String nuevoCodigoAdmin = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        Moderador moderador = Moderador.builder()
                .codigo(nuevoCodigoAdmin)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .nacionalidad(registerRequest.getNacionalidad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .genero(registerRequest.getGenero())
                .login(login)
                .build();

        return moderadorRepository.save(moderador);
    }

    public String ultimoCodigo() {
        return moderadorRepository.obtenerCodigo();
    }

    public Login activar(String codigoAdmin) {

        Moderador moderador = moderadorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = moderador.getLogin().getCodigo();

        return loginService.activar(codigoLogin);
    }

    public Login inactivar(String codigoAdmin) {

        Moderador moderador = moderadorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = moderador.getLogin().getCodigo();

        return loginService.inactivar(codigoLogin);
    }

    public Login suspender(String codigoAdmin) {

        Moderador moderador = moderadorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = moderador.getLogin().getCodigo();

        return loginService.suspender(codigoLogin);
    }

    public Login bloquear(String codigoAdmin) {

        Moderador moderador = moderadorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = moderador.getLogin().getCodigo();

        return loginService.bloquear(codigoLogin);
    }

    public Login eliminar(String codigoAdmin) {

        Moderador moderador = moderadorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = moderador.getLogin().getCodigo();

        return loginService.Eliminado(codigoLogin);
    }




}
