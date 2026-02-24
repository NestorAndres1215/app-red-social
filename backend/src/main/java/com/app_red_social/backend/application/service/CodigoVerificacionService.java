package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.constant.messages.NotFoundMessages;
import com.app_red_social.backend.domain.exception.ResourceNotFoundException;
import com.app_red_social.backend.domain.model.CodigoVerificacion;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.repository.CodigoVerificacionRepositoryPort;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.CodigoVerificacionUseCase;
import com.app_red_social.backend.infrastructure.util.GeneradorCodigo;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodigoVerificacionService implements CodigoVerificacionUseCase {

    private final CodigoVerificacionRepositoryPort codigoVerificacionRepositoryPort;
    private final LoginRepositoryPort loginRepositoryPort;
    private final JavaMailSender javaMailSender;

    @Override
    public List<CodigoVerificacion> listar() {
        return codigoVerificacionRepositoryPort.listar();
    }

    @Override
    public CodigoVerificacion verificacionRecuperacionContrasena(String correo) {
        Login login = loginRepositoryPort.findByEmail(correo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NotFoundMessages.EMAIL_NO_ENCONTRADO));
        String nuevoCodigo = Secuencia.generarSiguienteCodigo(codigoVerificacionRepositoryPort.obtenerUltimoCodigo());
        String codigoGenerado = GeneradorCodigo.generarCodigo();

        return codigoVerificacionRepositoryPort.findByCorreo(login.getEmail())
                .map(registroExistente -> {

                    registroExistente.setCodigoVerificacion(codigoGenerado);
                    registroExistente.setFechaGeneracion(LocalDateTime.now());

                    try {
                        codigoVerificacionCorreo(registroExistente);
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
                        codigoVerificacionCorreo(nuevo);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }

                    return registrar(nuevo);
                });
    }

    @Override
    public CodigoVerificacion registrar(CodigoVerificacion codigoVerificacion) {
        return codigoVerificacionRepositoryPort.registrar(codigoVerificacion);
    }

    @Override
    public CodigoVerificacion verificacionCodigo(String codigo) {

        CodigoVerificacion verificacion = listarCodigo(codigo);
        verificacion.setCodigoVerificacion(null);
        verificacion.setFechaGeneracion(LocalDateTime.now());

        return registrar(verificacion);
    }

    @Override
    public CodigoVerificacion listarCodigo(String codigo) {
        return codigoVerificacionRepositoryPort.listarCodigo(codigo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                NotFoundMessages.CODIGO_VERIFICACION_NO_ENCONTRADO));
    }

    @Override
    public void codigoVerificacionCorreo(CodigoVerificacion verificacion) throws MessagingException {

        final String destinatario = verificacion.getCorreo();
        final String asunto = "Código de verificación en dos pasos";
        final String codigo = verificacion.getCodigoVerificacion();
        final String usuario = verificacion.getUsuario();
        String contenidoHtml = """
                    <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh;">
                        <div style="background-color: #fff; padding: 20px; border: 1px solid #ccc; width: 80%%; max-width: 600px; text-align: center; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                
                            <h2 style="color: #2c3e50;">Hola, %s</h2>
                
                            <p style="color: #7f8c8d;">
                                Hemos recibido una solicitud para <strong>restablecer tu contraseña</strong> en Social Like.<br><br>
                                Para continuar con el proceso, utiliza el siguiente código de verificación:
                            </p>
                
                            <p>
                                <span style="font-size: 26px; font-weight: bold; color: #2980b9;">%s</span>
                            </p>
                
                            <p style="color: #7f8c8d;">
                                Este código es válido por <strong>15 minutos</strong>.  
                                Introdúcelo en la pantalla de verificación para completar el proceso de recuperación.
                            </p>
                
                            <p style="color: #7f8c8d;">
                                <strong>IMPORTANTE:</strong> No compartas este código con nadie.  
                                El equipo de Social Like <strong>nunca</strong> te pedirá este código por correo, llamadas ni mensajes.
                            </p>
                
                            <p style="color: #7f8c8d;">
                                Si tú no solicitaste este cambio, simplemente ignora este mensaje.  
                                Tu cuenta seguirá protegida.
                            </p>
                
                            <p style="font-size: 12px; color: #7f8c8d;">
                                Este es un mensaje automático. Por favor, no respondas a este correo.
                            </p>
                        </div>
                    </body>
                    </html>
                """.formatted(usuario, codigo);

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true);
        javaMailSender.send(mensaje);
    }
}
