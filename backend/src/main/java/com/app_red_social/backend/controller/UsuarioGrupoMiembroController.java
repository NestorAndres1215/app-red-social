package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.UsuarioGrupoMiembro;
import com.app_red_social.backend.service.UsuarioGrupoMiembroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario-grupo-miembros")
@Tag(name = "Usuario Grupo Miembros")
public class UsuarioGrupoMiembroController {

    private final UsuarioGrupoMiembroService usuarioGrupoMiembroService;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioGrupoMiembro>> listar() {
        return ResponseEntity.ok(usuarioGrupoMiembroService.listar());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<UsuarioGrupoMiembro> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoMiembroService.listarCodigo(codigo));
    }

    @GetMapping("/listar/fecha")
    public ResponseEntity<List<UsuarioGrupoMiembro>> listarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fecha
    ) {
        return ResponseEntity.ok(usuarioGrupoMiembroService.listarPorFecha(fecha));
    }

    @PutMapping("/cambiar-estado/{codigo}")
    public ResponseEntity<UsuarioGrupoMiembro> cambiarEstado(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoMiembroService.cambiarEstado(codigo, "ADMIN"));
    }

    @GetMapping("/grupo/nombre/{nombre}")
    public ResponseEntity<List<UsuarioGrupoMiembro>> listarPorNombreGrupo(@PathVariable String nombre) {
        return ResponseEntity.ok(usuarioGrupoMiembroService.listarPorNombreGrupo(nombre));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        usuarioGrupoMiembroService.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }

}
