package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.Moderador;
import com.app_red_social.backend.domain.port.repository.ModeradorRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.ModeradorEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.ModeradorMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaModeradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModeradorRepositoryAdapter implements ModeradorRepositoryPort {

    private final JpaModeradorRepository moderadorRepository;
    private final ModeradorMapper mapper;

    @Override
    public String ultimoCodigo() {
        return moderadorRepository.obtenerCodigo();
    }

    @Override
    public Optional<Moderador> listarModeradorCodigo(String codigo) {
        return moderadorRepository.findById(codigo).map(mapper::toDomain);
    }

    @Override
    public Optional<Moderador> listarUsername(String username) {
        return moderadorRepository.findByLogin_Username(username).map(mapper::toDomain);
    }

    @Override
    public Optional<Moderador> listarEmail(String email) {
        return moderadorRepository.findByLogin_Email(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Moderador> listarTelefono(String telefono) {
        return moderadorRepository.findByLogin_Telefono(telefono).map(mapper::toDomain);
    }

    @Override
    public List<Moderador> listarNombre(String nombre) {
        return moderadorRepository.findByNombre(nombre).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listarApellido(String apellido) {
        return moderadorRepository.findByApellido(apellido).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listarNombreAndApellido(String nombre, String apellido) {
        return moderadorRepository.findByNombreAndApellido(nombre,apellido).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listarGenero(String genero) {
        return moderadorRepository.findByGenero(genero).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listarEdad(Integer edad) {
        return moderadorRepository.findByEdad(edad).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listarEdadBetween(Integer edadMin, Integer edadMax) {
        return moderadorRepository.findByEdadBetween(edadMin,edadMax).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Moderador> listar() {
        return moderadorRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Moderador registrar(Moderador moderador) {
        ModeradorEntity entity = mapper.toEntity(moderador);
        ModeradorEntity saved = moderadorRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
