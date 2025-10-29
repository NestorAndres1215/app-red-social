package com.red_social.auth_service.controller;

import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.dto.response.UsuarioDetalleResponse;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Usuario;
import com.red_social.auth_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/listarId/{codigo}")
    public ResponseEntity<Usuario> listarId(@PathVariable String codigo) {
        return ResponseEntity.ok(usuarioService.listarId(codigo));
    }

    @GetMapping("/listarUsename/{username}")
    public ResponseEntity<List<Usuario>> listarUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.listarUsername(username));
    }

    @GetMapping("/listarEmail/{email}")
    public ResponseEntity<Usuario> listarEmail(@PathVariable String email) {
        return usuarioService.listarEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("No existe usuario con email: " + email));
    }


    @GetMapping("/listarTelefono/{telefono}")
    public ResponseEntity<List<Usuario>> listarTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(usuarioService.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(usuarioService.registrar(request));
    }
    @GetMapping("/detalle")
    public ResponseEntity<List<UsuarioDetalleResponse>> listarUsuariosConDetalle() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

}
