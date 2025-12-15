package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.HistorialUsuarioRequest;
import com.app_red_social.backend.dto.response.HistorialResponse;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.mapper.HistorialMapper;
import com.app_red_social.backend.model.HistorialUsuario;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.HistorialUsuarioRepository;
import com.app_red_social.backend.repository.LoginRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialUsuarioService {

    private final HistorialUsuarioRepository historialUsuarioRepository;
    private final LoginRepository loginRepository;
    private final HistorialMapper historialMapper;

    public String ultimoCodigo() {
        return historialUsuarioRepository.obtenerUltimoCodigo();
    }

    public HistorialUsuario registrar(HistorialUsuarioRequest historialUsuarioRequest) {

        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        Login login = loginRepository.findByUsername(historialUsuarioRequest.getUsuario())
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));

        HistorialUsuario historialUsuario = HistorialUsuario.builder()
                .codigo(nuevoCodigo)
                .estado(Estados.ACTIVO)
                .login(login)
                .titulo(historialUsuarioRequest.getTitulo())
                .modulo(historialUsuarioRequest.getModulo())
                .fechaRegistro(LocalDateTime.now())
                .detalle(historialUsuarioRequest.getDetalle())
                .build();

        return historialUsuarioRepository.save(historialUsuario);
    }

    public List<HistorialUsuario> listar() {
        return historialUsuarioRepository.findAll();
    }

    public HistorialUsuario listarCodigo(String codigo) {
        return historialUsuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }


    public List<HistorialResponse> listarHistorial(Integer opcion, String username, String estado) {

        List<Object[]> result = historialUsuarioRepository.obtenerHistorial(opcion, username, estado);

        return result.stream()
                .map(historialMapper::listarHistorial)
                .toList();
    }

    public HistorialUsuario inactivar(String codigo) {

        HistorialUsuario historial = historialUsuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        historial.setEstado(Estados.INACTIVO);
        return historialUsuarioRepository.save(historial);
    }
}
