package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.UsuarioGrupoMiembro;
import com.app_red_social.backend.service.UsuarioGrupoMiembroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        usuarioGrupoMiembroService.eliminar(codigo);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}
