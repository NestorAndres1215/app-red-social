package com.red_social.auth_service.service;

import com.red_social.auth_service.dto.request.SessionRequest;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.model.Sesion;
import com.red_social.auth_service.repository.LoginRepository;
import com.red_social.auth_service.repository.SesionRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SesionService {
    private final SesionRepository sesionRepository;
    private final LoginRepository loginRepository;

    public Sesion registrar(SessionRequest sessionRequest) {

        Login user = loginRepository.findByUsername(sessionRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("USUARIO NO ENCONTRADO"));

        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);

        Sesion session = Sesion.builder()
                .codigo(nuevoCodigo)
                .login(user)
                .navegador(sessionRequest.getNavegador())
                .ubicacion(sessionRequest.getUbicacion())
                .inicioSesion(LocalDateTime.now())
                .build();

        return sesionRepository.save(session);
    }

    public String ultimoCodigo() {
        return sesionRepository.obtenerCodigo();
    }

    public List<Sesion> listarPorUsername(String username) {
        return sesionRepository.findByLogin_Username(username);
    }

    public List<Sesion> listar() {
        return sesionRepository.findAll();
    }

    public Sesion listarId(String codigo) {
        return sesionRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la sesión con código: " + codigo
                ));
    }

}
