package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.CodigoVerificacion;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.CodigoVerificacionRepository;
import com.app_red_social.backend.util.GeneradorCodigo;
import com.app_red_social.backend.util.Secuencia;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodigoVerificacionService {

    private final CodigoVerificacionRepository codigoVerificacionRepository;
    private final LoginService loginService;
    private final CorreoService correoService;


    public String obtenerUltimoCodigo() {
        return codigoVerificacionRepository.obtenerUltimoCodigo();
    }

    public List<CodigoVerificacion> listar() {
        return codigoVerificacionRepository.findAll();
    }

    public CodigoVerificacion verificacionRecuperacionContrasena(String correo) {
        Login login = loginService.listarEmail(correo);
        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(codigoVerificacionRepository.obtenerUltimoCodigo());
        final String codigoGenerado = GeneradorCodigo.generarCodigo();
        return codigoVerificacionRepository.findByCorreo(login.getEmail())
                .map(registroExistente -> {

                    registroExistente.setCodigoVerificacion(codigoGenerado);
                    registroExistente.setFechaGeneracion(LocalDateTime.now());

                    try {
                        correoService.codigoVerificacionCorreo(registroExistente);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }

                    return registrar(registroExistente);
                })
                .orElseGet(() -> {
                    CodigoVerificacion nuevo = CodigoVerificacion.builder()
                            .codigo(nuevoCodigo)
                            .correo(login.getEmail())
                            .usuario(login.getUsername())
                            .tipoVerificacion("RECUPERACION_CONTRASENA")
                            .codigoVerificacion(codigoGenerado)
                            .fechaGeneracion(LocalDateTime.now())
                            .build();

                    try {
                        correoService.codigoVerificacionCorreo(nuevo);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }

                    return registrar(nuevo);
                });
    }

    public CodigoVerificacion registrar(CodigoVerificacion codigoVerificacion) {
        return codigoVerificacionRepository.save(codigoVerificacion);
    }


    public CodigoVerificacion verificacionCodigo(String codigo){

        CodigoVerificacion verificacion = listarCodigo(codigo);

        // Marcar como usado
        verificacion.setCodigoVerificacion(null);
        verificacion.setFechaGeneracion(LocalDateTime.now());

        return registrar(verificacion);
    }


    public CodigoVerificacion listarCodigo(String codigo) {
        return codigoVerificacionRepository.findByCodigoVerificacion(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.CODIGO_VERIFICACION_NO_ENCONTRADO));
    }

}
