package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.application.dto.usuario.RoleUserStatsResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioListaResponse;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.domain.port.repository.UsuarioRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.RoleUserStatsMapper;
import com.app_red_social.backend.infrastructure.persistence.mapper.UsuarioActualMapper;
import com.app_red_social.backend.infrastructure.persistence.mapper.UsuarioMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final UsuarioActualMapper usuarioActualMapper;
    private final RoleUserStatsMapper roleUserStatsMapper;

    @Override
    public String ultimoCodigo() {
        return repository.obtenerCodigo();
    }

    @Override
    public Optional<Usuario> buscarPorCodigo(String codigo) {
        return repository.findById(codigo)
                .map(mapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return repository.findByLogin_Username(username)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByLogin_Email(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorTelefono(String telefono) {
        return repository.findByLogin_Telefono(telefono)
                .map(mapper::toDomain);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        return repository.findByApellido(apellido)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.findByNombreAndApellido(nombre, apellido)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorVerificado(Boolean verificado) {
        return repository.findByVerificado(verificado.toString())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorCuentaPrivada(Boolean cuentaPrivada) {
        return repository.findByCuentaPrivada(cuentaPrivada)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorCiudad(String ciudad) {
        return repository.findByCiudad(ciudad)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorPais(String pais) {
        return repository.findByPais(pais)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorGenero(String genero) {
        return repository.findByGenero(genero)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorEdad(Integer edad) {
        return repository.findByEdad(edad)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorRangoEdad(Integer edadMin, Integer edadMax) {
        return repository.findByEdadBetween(edadMin, edadMax)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Usuario> buscarPorProveedor(String proveedor) {
        return repository.findByProvider(proveedor)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<UsuarioListaResponse> listarUsuarios(Integer option, String username, String estado) {
        List<Object[]> result = repository.listarUsuarios(option, username, estado);
        return mapper.usuarioListadoList(result);
    }

    @Override
    public List<RoleUserStatsResponse> listarPorcentajeTiempo(int opcion) {
        List<Object[]> results =repository.listarPorcentajeTiempo(opcion);
        return roleUserStatsMapper.toList(results);
    }

    @Override
    public List<UsuarioActualResponse> obtenerUsuarioActual(Integer opcion, String codigo) {
        List<Object[]> result = repository.obtenerUsuarioActual(opcion, codigo);
        return usuarioActualMapper.toList(result);
    }
}