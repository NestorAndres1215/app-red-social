package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Mensaje;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Menu;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RolService rolService;


    public List<Menu> listar() {
        return menuRepository.findAll();
    }

    public List<Menu> listarRoles(Rol rol) {
        return menuRepository.findByRol(rol);
    }

    public Menu listarCodigo(String codigo) {
        return menuRepository.findById(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Mensaje.CODIGO_NO_ENCONTRADO));
    }

    public List<Menu> obtenerMenuPorNivel(int nivel) {
        return menuRepository.findByNivel(nivel);
    }

    public List<Menu> obtenerMenusPorDosRoles(String rolCodigo) {
        rolService.listarCodigo(rolCodigo);
        List<String> codigos = List.of(rolCodigo);
        return menuRepository.findMenusByRolesCodigos(codigos);
    }


}
