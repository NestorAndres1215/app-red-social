package com.red_social.auth_service.service;

import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.EstadoUsuario;
import com.red_social.auth_service.repository.EstadoUsuarioRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoUsuarioService {

    private final EstadoUsuarioRepository estadoUsuarioRepository;

    public List<EstadoUsuario> listarEstadoUsuario() {
        return estadoUsuarioRepository.findAll();
    }

    public EstadoUsuario listarPorCodigo(String id) {
        return estadoUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el estado de usuario con código: " + id));
    }
    public EstadoUsuario registrar(EstadoUsuario estadoUsuario) {
        if (estadoUsuario == null) {
            throw new IllegalArgumentException("El estado de usuario no puede ser nulo");
        }
        estadoUsuarioRepository.findByNombre(estadoUsuario.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException(
                            "El estado de usuario '" + estadoUsuario.getNombre() + "' ya existe");
                });
        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);

        estadoUsuario.setCodigo(nuevoCodigo);

        return estadoUsuarioRepository.save(estadoUsuario);
    }

    public EstadoUsuario actualizar(EstadoUsuario estadoUsuario) {
        EstadoUsuario estado = estadoUsuarioRepository.findById(estadoUsuario.getCodigo())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de usuario no encontrado con código: " + estadoUsuario.getCodigo()));

        estado.setNombre(estadoUsuario.getNombre());
        estado.setDescripcion(estadoUsuario.getDescripcion());

        return estadoUsuarioRepository.save(estado);
    }

    public String ultimoCodigo() {
        return estadoUsuarioRepository.obtenerCodigo();
    }
}
