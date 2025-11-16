package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.model.Usuario;
import com.app_red_social.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

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



}
