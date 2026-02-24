package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.application.dto.historial.HistorialResponse;
import com.app_red_social.backend.domain.model.HistorialUsuario;
import com.app_red_social.backend.domain.port.repository.HistorialRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.HistorialUsuarioEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.HistorialMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaHistorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistorialRepositoryAdapter implements HistorialRepositoryPort {


    private final JpaHistorialRepository repository;
    private final HistorialMapper mapper;

    @Override
    public String ultimoCodigo() {
        return repository.obtenerUltimoCodigo();
    }

    @Override
    public List<HistorialUsuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<HistorialUsuario> buscarPorCodigo(String codigo) {
        return repository.findById(codigo).map(mapper::toDomain);
    }

    @Override
    public HistorialUsuario registrar(HistorialUsuario request) {
        HistorialUsuarioEntity entity = mapper.toEntity(request);
        HistorialUsuarioEntity saved = repository.save(entity);
        return mapper.toDomain(saved);

    }

    @Override
    public List<HistorialResponse> listarHistorial(Integer opcion, String username, String estado) {
        List<Object[]> result = repository.obtenerHistorial(opcion, username, estado);

        return result.stream()
                .map(mapper::listarHistorial)
                .toList();
    }
}
