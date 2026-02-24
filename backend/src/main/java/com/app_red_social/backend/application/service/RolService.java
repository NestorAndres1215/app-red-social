package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.repository.RolRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.RolUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService implements RolUseCase {

    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public List<Rol> listarTodos() {
        return rolRepositoryPort.listarTodos();
    }

    @Override
    public Rol buscarPorNombre(String nombre) {
        return rolRepositoryPort.buscarPorNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.ROL_NO_ENCONTRADO));
    }

    @Override
    public Rol buscarPorCodigo(String codigo) {
        return rolRepositoryPort.buscarPorCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }
}
