package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.DuplicateErrorMessages;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.EstadoUsuario;
import com.app_red_social.backend.repository.EstadoUsuarioRepository;
import com.app_red_social.backend.util.Secuencia;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoUsuarioService {

    private final EstadoUsuarioRepository estadoUsuarioRepository;

    public List<EstadoUsuario> listar() {
        return estadoUsuarioRepository.findAll();
    }

    public String ultimoCodigo() {
        return estadoUsuarioRepository.obtenerCodigo();
    }

    public EstadoUsuario listarCodigo(String codigo) {
        return estadoUsuarioRepository.findById(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }


    public EstadoUsuario registrar(EstadoUsuario estadoUsuario) {

        estadoUsuarioRepository.findByNombre(estadoUsuario.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException(DuplicateErrorMessages.ESTADO_USUARIO_EXISTENTE);
                });

        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());
        estadoUsuario.setCodigo(nuevoCodigo);

        return estadoUsuarioRepository.save(estadoUsuario);
    }

}
