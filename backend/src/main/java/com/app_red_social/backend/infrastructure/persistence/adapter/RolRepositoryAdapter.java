package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.repository.RolRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.RolMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolRepositoryAdapter implements RolRepositoryPort {

    private final JpaRolRepository repository;
    private  final RolMapper mapper;

    @Override
    public List<Rol> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Rol> buscarPorCodigo(String codigo) {
        return repository.findById(codigo)
                .map(mapper::toDomain);
    }


}
