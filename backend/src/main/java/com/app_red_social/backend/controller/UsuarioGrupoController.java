package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.UsuarioGrupoRequest;
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
}
