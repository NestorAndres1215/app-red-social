package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.dto.request.RegisterRequest;

import com.app_red_social.backend.dto.response.AdministradorActualResponse;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.mapper.AdministradorActualMapper;
import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.AdministradorRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final LoginService loginService;


    public Administrador listarAdministradorCodigoUser(String usuarioCodigo) {
        return administradorRepository.findByLogin_Codigo(usuarioCodigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Administrador listarUsername(String username) {
        return administradorRepository.findByLogin_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    public Administrador listarEmail(String email) {
        return administradorRepository.findByLogin_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
    }

    public Administrador listarTelefono(String telefono) {
        return administradorRepository.findByLogin_Telefono(telefono)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.TELEFONO_NO_ENCONTRADO));
    }

    public List<Administrador> listar() {
        return administradorRepository.findAll();
    }

    public Administrador listarId(String codigo) {
        return administradorRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Administrador registrar(RegisterRequest registerRequest) {

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

        Administrador admin = Administrador.builder()
                .codigo(nuevoCodigoAdmin)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .genero(registerRequest.getGenero())
                .login(login)
                .build();

        return administradorRepository.save(admin);
    }

    public String ultimoCodigo() {
        return administradorRepository.obtenerCodigo();
    }


    public AdministradorActualResponse obtenerPorLogin(String loginCodigo) {

        List<Object[]> result = administradorRepository.obtenerAdministradorPorLogin(loginCodigo);

        if (result.isEmpty()) {
            throw new RuntimeException("Administrador no encontrado con login: " + loginCodigo);
        }

        return AdministradorActualMapper.toDto(result.get(0));
    }


    public Login activar(String codigoAdmin) {

        Administrador admin = administradorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = admin.getLogin().getCodigo();

        return loginService.activar(codigoLogin);
    }

    public Login inactivar(String codigoAdmin) {

        Administrador admin = administradorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = admin.getLogin().getCodigo();

        return loginService.inactivar(codigoLogin);
    }

    public Login suspender(String codigoAdmin) {

        Administrador admin = administradorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = admin.getLogin().getCodigo();

        return loginService.suspender(codigoLogin);
    }

    public Login bloquear(String codigoAdmin) {

        Administrador admin = administradorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = admin.getLogin().getCodigo();

        return loginService.bloquear(codigoLogin);
    }

    public Login eliminar(String codigoAdmin) {

        Administrador admin = administradorRepository.findById(codigoAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        String codigoLogin = admin.getLogin().getCodigo();

        return loginService.Eliminado(codigoLogin);
    }
}
