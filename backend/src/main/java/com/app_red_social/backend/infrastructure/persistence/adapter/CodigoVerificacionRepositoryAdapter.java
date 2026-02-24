package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.CodigoVerificacion;
import com.app_red_social.backend.domain.port.repository.CodigoVerificacionRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.mapper.CodigoVerificacionMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaCodigoVerificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CodigoVerificacionRepositoryAdapter implements CodigoVerificacionRepositoryPort {

    private  final JpaCodigoVerificacionRepository repository;
    private  final CodigoVerificacionMapper mapper;

    @Override
    public String obtenerUltimoCodigo() {
        return  repository.obtenerUltimoCodigo();
    }

    @Override
    public Optional<CodigoVerificacion> findByCorreo(String correo) {
        return repository.findByCorreo(correo)
                .map(mapper::toDomain);
    }

    @Override
    public List<CodigoVerificacion> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public CodigoVerificacion verificacionRecuperacionContrasena(CodigoVerificacion codigoVerificacion) {
        return null;
    }

    @Override
    public CodigoVerificacion registrar(CodigoVerificacion codigoVerificacion) {
        return null;
    }

    @Override
    public CodigoVerificacion verificacionCodigo(CodigoVerificacion codigoVerificacion) {
        return null;
    }

    @Override
    public  Optional<CodigoVerificacion> listarCodigo(String codigo) {
        return null;
    }
}
