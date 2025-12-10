package com.app_red_social.backend.service;

import com.app_red_social.backend.model.CodigoVerificacion;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.CodigoVerificacionRepository;
import com.app_red_social.backend.util.GeneradorCodigo;
import com.app_red_social.backend.util.Secuencia;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

                    registroExistente.setCodigo_verificacion(codigoGenerado);
                    registroExistente.setFecha_generacion(LocalDateTime.now());


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
                            .tipo_verificacion("RECUPERACION_CONTRASENA")
                            .codigo_verificacion(codigoGenerado)
                            .fecha_generacion(LocalDateTime.now())
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


}
