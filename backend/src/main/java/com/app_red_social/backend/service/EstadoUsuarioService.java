package com.app_red_social.backend.service;

import com.app_red_social.backend.exception.BadRequestException;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.EstadoUsuario;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.repository.EstadoUsuarioRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app_red_social.backend.constants.Mensaje.*;

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
                        new ResourceNotFoundException(CODIGO_NO_ENCONTRADO));
    }

    public EstadoUsuario registrar(EstadoUsuario estadoUsuario) {

        estadoUsuarioRepository.findByNombre(estadoUsuario.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException(
                            String.format(ESTADO_USUARIO_YA_EXISTE, estadoUsuario.getNombre().toUpperCase()));
                });

        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo);

        estadoUsuario.setCodigo(nuevoCodigo);

        return estadoUsuarioRepository.save(estadoUsuario);
    }


}
