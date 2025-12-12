package com.app_red_social.backend.controller;

import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.dto.request.UsuarioGrupoRequest;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.model.UsuarioGrupo;
import com.app_red_social.backend.service.UsuarioGrupoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario-grupo")
@Tag(name = "Usuario Grupo")
public class UsuarioGrupoController {

    private final UsuarioGrupoService usuarioGrupoService;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioGrupo>> listar() {
        return ResponseEntity.ok(usuarioGrupoService.listar());
    }

    @PutMapping(value = "/actualizar/foto/{codigo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioGrupo> actualizarFoto(@PathVariable String codigo, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(usuarioGrupoService.actualizarFoto(codigo, file));
    }

    @PostMapping
    public ResponseEntity<UsuarioGrupo> registrar(@Valid @RequestBody UsuarioGrupoRequest dto) {
        return ResponseEntity.ok(usuarioGrupoService.registrar(dto));
    }

    @PutMapping("/inactivar/{codigo}")
    public ResponseEntity<UsuarioGrupo> eliminar(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.inactivar(codigo));
    }

    @PutMapping("/privacidad/publico/{codigo}")
    public ResponseEntity<UsuarioGrupo> publico(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.publico(codigo));
    }

    @PutMapping("/privacidad/privado/{codigo}")
    public ResponseEntity<UsuarioGrupo> privado(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioGrupoService.privado(codigo));
    }


    @GetMapping("/listar/estado/activo")
    public ResponseEntity<List<UsuarioGrupo>> listarPorEstadoActivo() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorEstado(Estados.ACTIVO));
    }

    @GetMapping("/listar/estado/inactivo")
    public ResponseEntity<List<UsuarioGrupo>> listarPorEstadoInactivo() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorEstado(Estados.INACTIVO));
    }

    @GetMapping("/listar/privacidad-publico/estado-activo")
    public ResponseEntity<List<UsuarioGrupo>> listarActivoPublico() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorPrivacidadYEstado(Estados.PUBLICO, Estados.ACTIVO));
    }

    @GetMapping("/listar/privacidad-publico/estado-inactivo")
    public ResponseEntity<List<UsuarioGrupo>> listarInactivoPublico() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorPrivacidadYEstado(Estados.PUBLICO, Estados.INACTIVO));
    }

    @GetMapping("/listar/privacidad-privado/estado-inactivo")
    public ResponseEntity<List<UsuarioGrupo>> listarActivoPrivado() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorPrivacidadYEstado(Estados.PRIVADO, Estados.ACTIVO));
    }

    @GetMapping("/listar/privacidad-privado/estado-inactivo")
    public ResponseEntity<List<UsuarioGrupo>> listarInactivoPrivado() {
        return ResponseEntity.ok(usuarioGrupoService.listarPorPrivacidadYEstado(Estados.PRIVADO, Estados.INACTIVO));
    }
}
