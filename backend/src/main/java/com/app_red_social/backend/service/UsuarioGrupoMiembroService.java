package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.model.UsuarioGrupoMiembro;
import com.app_red_social.backend.repository.UsuarioGrupoMiembroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioGrupoMiembroService {

    private final UsuarioGrupoMiembroRepository usuarioGrupoMiembroRepository;

    public List<UsuarioGrupoMiembro> listar() {
        return usuarioGrupoMiembroRepository.findAll();
    }

    public UsuarioGrupoMiembro listarCodigo(String codigo) {
        return usuarioGrupoMiembroRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public List<UsuarioGrupoMiembro> listarPorFecha(LocalDateTime fecha) {
        return usuarioGrupoMiembroRepository.findByFechaRegistro(fecha);
    }

    public UsuarioGrupoMiembro cambiarEstado(String codigo, String nuevoEstado) {

        UsuarioGrupoMiembro miembro = usuarioGrupoMiembroRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NotFoundMessages.CODIGO_NO_ENCONTRADO
                ));

        miembro.setEstadoMiembro(nuevoEstado);

        return usuarioGrupoMiembroRepository.save(miembro);
    }

    public List<UsuarioGrupoMiembro> listarPorNombreGrupo(String nombreGrupo) {
        return usuarioGrupoMiembroRepository.findByUsuarioGrupo_Nombre(nombreGrupo);
    }

    public void eliminar(String codigo) {
        UsuarioGrupoMiembro miembro = listarCodigo(codigo);
        usuarioGrupoMiembroRepository.delete(miembro);
    }
}

