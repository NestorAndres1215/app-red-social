package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.EstadoUsuario;
import com.app_red_social.backend.domain.port.repository.EstadoRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.EstadoUsuarioEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.EstadoMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaEstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EstadoRepositoryAdapter implements EstadoRepositoryPort {

    private final JpaEstadoRepository repository;
    private final EstadoMapper mapper;

    @Override
    public List<EstadoUsuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<EstadoUsuario> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<EstadoUsuario> buscarPorCodigo(String codigo) {
        return repository.findById(codigo)
                .map(mapper::toDomain);
    }



}
