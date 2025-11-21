package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.dto.response.UsuarioListaResponse;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Usuario> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioService.listarCodigo(codigo));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<Usuario> obtenerPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.listarUsername(username));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Usuario> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.listarEmail(email));
    }

    @GetMapping("/listar/telefono/{telefono}")
    public ResponseEntity<Usuario> obtenerPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(usuarioService.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(usuarioService.registrar(request));
    }

    @GetMapping("/listar/usuarios/normal/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosNormal(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioService.listarUsuarios(1, username, estado));
    }

    @GetMapping("/listar/usuarios/administradores/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosAdmin(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioService.listarUsuarios(2, username, estado));
    }

    @GetMapping("/listar/usuarios/moderador/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosModerador(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioService.listarUsuarios(3, username, estado));
    }


}
