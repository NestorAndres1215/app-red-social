package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Mensaje;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public String ultimoCodigo() {
        return loginRepository.obtenerCodigo();
    }

    public Login listarCodigo(String codigo) {
        return loginRepository.findById(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Mensaje.CODIGO_NO_ENCONTRADO));
    }

    public Login listarUsername(String username) {
        return loginRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Mensaje.USERNAME_NO_ENCONTRADO));
    }

    public Login listarEmail(String email) {
        return loginRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Mensaje.EMAIL_NO_ENCONTRADO));
    }


    public Login listarTelefono(String telefono) {
        return loginRepository.findByTelefono(telefono)
                .orElseThrow(() ->
                        new ResourceNotFoundException(Mensaje.TELEFONO_NO_ENCONTRADO));
    }


}
