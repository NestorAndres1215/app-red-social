package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.usecases.LoginUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
@Tag(name = "Login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PutMapping("/{codigo}/activar")
    public ResponseEntity<Login> activar(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.activar(codigo));
    }

    @PutMapping("/{codigo}/inactivar")
    public  ResponseEntity<Login>  inactivar(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.inactivar(codigo));
    }

    @PutMapping("/{codigo}/suspender")
    public  ResponseEntity<Login>  suspender(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.suspender(codigo));
    }

    @PutMapping("/{codigo}/bloquear")
    public  ResponseEntity<Login>  bloquear(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.bloquear(codigo));
    }

    @PutMapping("/{codigo}/eliminar")
    public  ResponseEntity<Login>  eliminar(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.eliminado(codigo));
    }

    @PutMapping("/{codigo}/inhabilitar")
    public  ResponseEntity<Login>  inhabilitar(@PathVariable String codigo) {
        return ResponseEntity.ok(loginUseCase.inhabilitado(codigo));
    }

    @PutMapping("/ultimo-login/{username}")
    public ResponseEntity<Login> registrar(@PathVariable String username) {
        return ResponseEntity.ok(loginUseCase.ultimoLogueo(username));
    }


}


