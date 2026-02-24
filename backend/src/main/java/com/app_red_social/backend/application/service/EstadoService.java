package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.EstadoUsuario;
import com.app_red_social.backend.domain.port.repository.EstadoRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.EstadoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EstadoService implements EstadoUseCase {

    private final EstadoRepositoryPort estadoUsuarioRepository;

    @Override
    public List<EstadoUsuario> listarTodos() {
        return estadoUsuarioRepository.listarTodos();
    }

    @Override
    public EstadoUsuario buscarPorNombre(String nombre) {
        return estadoUsuarioRepository.buscarPorNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ESTADO_USUARIO_NO_ENCONTRADO));
    }

    @Override
    public EstadoUsuario buscarPorCodigo(String codigo) {
        return estadoUsuarioRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }
}
