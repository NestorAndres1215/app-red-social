package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.ContrasenaRequest;
import com.app_red_social.backend.dto.request.LoginRequest;
import com.app_red_social.backend.dto.response.TokenResponse;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/generate-token")
    public ResponseEntity<TokenResponse> generarToken(@Valid @RequestBody LoginRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<Login> obtenerUsuarioActual(Principal principal) {
        return ResponseEntity.ok(authService.actualUsuario(principal));
    }

    @GetMapping("/listar-login")
    public ResponseEntity<List<Login>> obtenerLogin() {
        return ResponseEntity.ok(authService.listarLogin());
    }

    @GetMapping("/listar/moderador")
    public ResponseEntity<List<Login>> listar() {
        return ResponseEntity.ok(authService.listarModeradores());
    }

    @PostMapping("/ultimo-login/{username}")
    public ResponseEntity<Login> registrar(@PathVariable String username) {
        return ResponseEntity.ok(authService.ultimoLogueo(username));
    }

    @PutMapping("/actualizar/contrasenia")
    public ResponseEntity<Login> actualizarContrasenia(@Valid @RequestBody ContrasenaRequest contrasenaRequest) {
        return ResponseEntity.ok(authService.cambiarContrasena(contrasenaRequest));
    }


}



