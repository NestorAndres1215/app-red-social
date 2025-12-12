package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.UsuarioGrupoRequest;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.UsuarioGrupo;
import com.app_red_social.backend.service.UsuarioGrupoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario-grupo")
@Tag(name = "Usuario Grupo")
public class UsuarioGrupoController {

    private final UsuarioGrupoService usuarioGrupoService;

    @PutMapping(value = "/actualizar/foto/{codigo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioGrupo> actualizarFoto(@PathVariable String codigo, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(usuarioGrupoService.actualizarFoto(codigo, file));
    }

    @PostMapping
    public ResponseEntity<UsuarioGrupo> registrar(@RequestBody UsuarioGrupoRequest dto) {
        return ResponseEntity.ok(usuarioGrupoService.registrar(dto));
    }

    @DeleteMapping("/inactivar/{codigo}")
    public ResponseEntity<UsuarioGrupo> eliminar(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.inactivar(codigo));
    }

    @PutMapping("/privacidad/publico/{codigo}")
    public ResponseEntity<UsuarioGrupo> publico(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.publico(codigo));
    }

    @PutMapping("/privacidad/privado/{codigo}")
    public ResponseEntity<UsuarioGrupo> privado(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.publico(codigo));
    }

}
