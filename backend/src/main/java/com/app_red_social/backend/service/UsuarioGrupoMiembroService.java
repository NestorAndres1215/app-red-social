package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.UsuarioGrupoMiembroRequest;
import com.app_red_social.backend.exception.BadRequestException;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.UsuarioGrupo;
import com.app_red_social.backend.model.UsuarioGrupoMiembro;
import com.app_red_social.backend.repository.LoginRepository;
import com.app_red_social.backend.repository.UsuarioGrupoMiembroRepository;
import com.app_red_social.backend.repository.UsuarioGrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioGrupoMiembroService {

    private final UsuarioGrupoMiembroRepository usuarioGrupoMiembroRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final LoginRepository loginRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

        miembro.setEstadoMiembro(nuevoEstado);

        return usuarioGrupoMiembroRepository.save(miembro);
    }

    public List<UsuarioGrupoMiembro> listarPorNombreGrupo(String nombreGrupo) {
        return usuarioGrupoMiembroRepository.findByUsuarioGrupo_Nombre(nombreGrupo);
    }

    public List<UsuarioGrupoMiembro> registrarListaDTO(
            List<UsuarioGrupoMiembroRequest> dtos) {

        if (dtos == null || dtos.isEmpty()) {
            throw new BadRequestException(NotFoundMessages.LISTAR_VACIO);
        }

        List<UsuarioGrupoMiembro> entidades = dtos.stream().map(dto -> {

            UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findById(dto.getGrupo())
                    .orElseThrow(() -> new RuntimeException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

            Login login = loginRepository.findById(dto.getUsuario())
                    .orElseThrow(() -> new RuntimeException(NotFoundMessages.CODIGO_NO_ENCONTRADO));

            return UsuarioGrupoMiembro.builder()
                    .codigo("")
                    .estadoMiembro(Estados.ACTIVO)
                    .fechaRegistro(LocalDateTime.now())
                    .usuarioGrupo(usuarioGrupo)
                    .login(login)
                    .build();

        }).toList();

        return usuarioGrupoMiembroRepository.saveAll(entidades);
    }

    public void eliminar(String codigo) {
        UsuarioGrupoMiembro miembro = listarCodigo(codigo);
        usuarioGrupoMiembroRepository.delete(miembro);
    }
}

