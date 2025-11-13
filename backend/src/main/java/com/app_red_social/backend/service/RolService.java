package com.app_red_social.backend.service;


import com.app_red_social.backend.exception.BadRequestException;
import com.app_red_social.backend.exception.ResourceAlreadyExistsException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.repository.RolRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app_red_social.backend.constants.Mensaje.*;
import static com.app_red_social.backend.constants.Roles.*;


@Service
@RequiredArgsConstructor
public class RolService {

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
                        new ResourceNotFoundException(CODIGO_NO_ENCONTRADO));
    }

    public Rol registrar(Rol rol) {
        validarRolPermitido(rol.getNombre());
        validarLimitesRoles();
        rolRepository.findByNombre(rol.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException("El rol '" + rol.getNombre() + "' ya existe");
                });

        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo);

        rol.setCodigo(nuevoCodigo);

        return rolRepository.save(rol);
    }

    private void validarRolPermitido(String nombreRol) {
        List<String> rolesPermitidos = List.of(
                ROLE_ADMIN,
                ROLE_USER,
                ROLE_MODERADOR
        );

        if (!rolesPermitidos.contains(nombreRol.toUpperCase())) {
            throw new BadRequestException(ROL_NO_PERMITIDO);
        }
    }


    private void validarLimitesRoles() {
        long totalRoles = rolRepository.count();

        if (totalRoles >= 3) {
            throw new BadRequestException(LIMITE_ROLES_SUPERADO);
        }
    }
}
