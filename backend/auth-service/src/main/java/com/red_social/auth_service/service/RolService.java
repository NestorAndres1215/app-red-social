package com.red_social.auth_service.service;

import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Rol;
import com.red_social.auth_service.repository.RolRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;


    public Rol registrar(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        rolRepository.findByNombre(rol.getNombre())
                .ifPresent(r -> {
                    throw new ResourceAlreadyExistsException("El rol '" + rol.getNombre() + "' ya existe");
                });

        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);

        rol.setCodigo(nuevoCodigo);

        return rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol listarId(String id) {
        return rolRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontró el rol con código: " + id));
    }

    // Actualizar Role
    public Rol actualizar(Rol updatedRole) {
        Rol role = rolRepository.findById(updatedRole.getCodigo())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        role.setNombre(updatedRole.getNombre());
        role.setDescripcion(updatedRole.getDescripcion());

        return rolRepository.save(role);
    }

    public String ultimoCodigo() {
        return rolRepository.obtenerCodigo();
    }
}
