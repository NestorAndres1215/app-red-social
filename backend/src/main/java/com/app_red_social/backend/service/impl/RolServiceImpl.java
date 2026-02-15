package com.app_red_social.backend.service.impl;


import com.app_red_social.backend.constants.*;
import com.app_red_social.backend.constants.messages.DuplicateErrorMessages;
import com.app_red_social.backend.constants.messages.GlobalErrorMessages;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.BadRequestException;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.repository.RolRepository;
import com.app_red_social.backend.service.RolService;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public String ultimoCodigo() {
        return rolRepository.obtenerCodigo();
    }

    public Rol listarCodigo(String codigo) {
        return rolRepository.findById(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public Rol listarNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.ROL_NO_ENCONTRADO));
    }

    public Rol registrar(Rol rol) {

        rolRepository.findByNombre(rol.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException(DuplicateErrorMessages.ROL_EXISTENTE);
                });

       final  String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        rol.setCodigo(nuevoCodigo);

        return rolRepository.save(rol);
    }



}
