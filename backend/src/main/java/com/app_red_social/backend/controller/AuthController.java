package com.app_red_social.backend.controller;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/generate-token")
    public ResponseEntity<TokenResponse> generarToken(@RequestBody LoginRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<Login> obtenerUsuarioActual(Principal principal) {
        return ResponseEntity.ok(authService.actualUsuario(principal));
    }
}



