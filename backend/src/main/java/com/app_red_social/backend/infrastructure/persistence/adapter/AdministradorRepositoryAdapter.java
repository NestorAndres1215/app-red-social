package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.domain.model.Administrador;
import com.app_red_social.backend.domain.port.repository.AdministradorRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.AdministradorEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.AdministradorMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaAdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdministradorRepositoryAdapter implements AdministradorRepositoryPort {

    private final JpaAdministradorRepository administradorRepository;
    private final AdministradorMapper mapper;

    @Override
    public String ultimoCodigo() {
        return administradorRepository.obtenerCodigo();
    }

    @Override
    public Optional<Administrador> listarModeradorCodigo(String codigo) {
        return administradorRepository.findById(codigo).map(mapper::toDomain);
    }

    @Override
    public Optional<Administrador> listarUsername(String username) {
        return administradorRepository.findByLogin_Username(username).map(mapper::toDomain);
    }

    @Override
    public Optional<Administrador> listarEmail(String email) {
        return administradorRepository.findByLogin_Email(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Administrador> listarTelefono(String telefono) {
        return administradorRepository.findByLogin_Telefono(telefono).map(mapper::toDomain);
    }

    @Override
    public List<Administrador> listarNombre(String nombre) {
        return administradorRepository.findByNombre(nombre).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listarApellido(String apellido) {
        return administradorRepository.findByApellido(apellido).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listarNombreAndApellido(String nombre, String apellido) {
        return administradorRepository.findByNombreAndApellido(nombre, apellido).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listarGenero(String genero) {
        return administradorRepository.findByGenero(genero).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listarEdad(Integer edad) {
        return administradorRepository.findByEdad(edad).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listarEdadBetween(Integer edadMin, Integer edadMax) {
        return administradorRepository.findByEdadBetween(edadMin, edadMax).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrador> listar() {
        return administradorRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<AdministradorActualResponse> obtenerPorLogin(String loginCodigo) {
        List<Object[]> result = administradorRepository.obtenerAdministradorPorLogin(loginCodigo);
        return mapper.toList(result);
    }

    @Override
    public Administrador registrar(Administrador administrador) {
        AdministradorEntity entity = mapper.toEntity(administrador);
        AdministradorEntity saved = administradorRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
