package mx.unam.aragon.zorrito.service.Correo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoConAdjunto(String destinatario, String asunto, String cuerpo, byte[] pdfBytes, String nombreAdjunto) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(cuerpo, true); // HTML permitido

        InputStreamSource attachment = new ByteArrayResource(pdfBytes);
        helper.addAttachment(nombreAdjunto, attachment);

        mailSender.send(mensaje);
    }

}
