package com.app_red_social.backend.service;

import com.app_red_social.backend.model.CodigoVerificacion;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorreoService {

    private final JavaMailSender javaMailSender;

    public void codigoVerificacionCorreo(CodigoVerificacion verificacion) throws MessagingException {

        final String destinatario = verificacion.getCorreo();
        final String asunto = "Código de verificación en dos pasos";
        final String codigo = verificacion.getCodigo_verificacion();
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
