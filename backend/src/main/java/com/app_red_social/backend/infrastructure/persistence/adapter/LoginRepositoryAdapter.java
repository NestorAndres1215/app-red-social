package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.LoginEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.LoginMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginRepositoryAdapter implements LoginRepositoryPort {

    private final JpaLoginRepository repository;
    private  final LoginMapper mapper;

    @Override
    public String ultimoCodigo() {
        return repository.obtenerCodigo();
    }

    @Override
    public Optional<Login> findByCodigo(String username) {
        return repository.findById(username).map(mapper::toDomain);
    }

    @Override
    public Optional<Login> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<Login> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Login> findByTelefono(String telefono) {
        return repository.findByTelefono(telefono).map(mapper::toDomain);
    }

    @Override
    public Login save(Login login) {
        LoginEntity loginEntity = mapper.toEntity(login);
        LoginEntity saved = repository.save(loginEntity);
        return mapper.toDomain(saved);
    }

}
