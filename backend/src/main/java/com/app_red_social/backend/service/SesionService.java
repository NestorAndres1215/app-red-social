package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.SessionRequest;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Sesion;
import com.app_red_social.backend.repository.SesionRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SesionService {

    private final SesionRepository sesionRepository;
    private final LoginService loginService;

    public String ultimoCodigo() {
        return sesionRepository.obtenerCodigo();
    }

    public Sesion listarPorUsername(String username) {
        return sesionRepository.findByLogin_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

    public List<Sesion> listar() {
        return sesionRepository.findAll();
    }

    public Sesion listarCodigo(String codigo) {
        return sesionRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }


    public Sesion registrar(SessionRequest sessionRequest) {

        Login user = loginService.listarUsername(sessionRequest.getUsername());
        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        Sesion session = Sesion.builder()
                .codigo(nuevoCodigo)
                .login(user)
                .navegador(sessionRequest.getNavegador())
                .ubicacion(sessionRequest.getUbicacion())
                .inicioSesion(LocalDateTime.now())
                .build();

        return sesionRepository.save(session);
    }

}
