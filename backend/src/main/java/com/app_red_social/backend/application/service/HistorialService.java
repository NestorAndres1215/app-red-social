package com.app_red_social.backend.application.service;

import com.app_red_social.backend.application.dto.historial.HistorialResponse;
import com.app_red_social.backend.application.dto.historial.HistorialUsuarioRequest;
import com.app_red_social.backend.domain.constant.Estados;
import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.HistorialUsuario;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.repository.HistorialRepositoryPort;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.HistorialUseCase;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistorialService implements HistorialUseCase {
    private final HistorialRepositoryPort historialRepositoryPort;
    private  final LoginRepositoryPort loginRepositoryPort;

    @Override
    public List<HistorialUsuario> listarTodos() {
        return historialRepositoryPort.listarTodos();
    }

    @Override
    public Optional<HistorialUsuario> buscarPorCodigo(String codigo) {
        return historialRepositoryPort.buscarPorCodigo(codigo);
    }

    @Override
    public HistorialUsuario registrar(HistorialUsuarioRequest request) {

        String nuevoCodigo = Secuencia.generarSiguienteCodigo(historialRepositoryPort.ultimoCodigo());

        Login login = loginRepositoryPort.findByUsername(request.getUsuario())
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));

        HistorialUsuario historialUsuario = HistorialUsuario.builder()
                .codigo(nuevoCodigo)
                .estado(Estados.ACTIVO)
                .login(login)
                .titulo(request.getTitulo())
                .modulo(request.getModulo())
                .fechaRegistro(LocalDateTime.now())
                .detalle(request.getDetalle())
                .build();
        return historialRepositoryPort.registrar(historialUsuario);
    }

    @Override
    public List<HistorialResponse> listarHistorial(Integer opcion, String username, String estado) {
        return historialRepositoryPort.listarHistorial(opcion, username, estado);
    }

    @Override
    public HistorialUsuario inactivar(String codigo) {
        HistorialUsuario historial = historialRepositoryPort.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        historial.setEstado(Estados.INACTIVO);
        return historialRepositoryPort.registrar(historial);
    }
}
