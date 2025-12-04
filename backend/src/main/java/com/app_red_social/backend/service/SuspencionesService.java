package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Suspensiones;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.repository.SuspensionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuspencionesService {

    private  final SuspensionesRepository suspensionesRepository;

    public String obtenerUltimoCodigo() {
        return suspensionesRepository.obtenerUltimoCodigo();
    }
    public List<Suspensiones> listar() {
        return suspensionesRepository.findAll();
    }

    public Suspensiones listarPorUsuario(Usuario usuario) {
        return suspensionesRepository.findByUsuario(usuario)
                .orElseThrow(() ->
                new ResourceNotFoundException(NotFoundMessages.USERNAME_NO_ENCONTRADO));
    }

}
