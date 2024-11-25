package adri.suivi_courrier.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.dto.EmailDetailsDto;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendApprovalEmail(EmailDetailsDto details) throws Exception {
        try {
            LOGGER.info("Envoi d'un email d'approbation à {}", details.getRecipient());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(details.getRecipient());
            message.setSubject("Votre compte a été approuvé");
            message.setText("Félicitations, votre compte a été approuvé !");
            emailSender.send(message);
        } catch (MailException e) {
            LOGGER.error("Erreur lors de l'envoi de l'email d'approbation", e);
            throw new Exception("Erreur lors de l'envoi de l'email d'approbation : " + e.getMessage());
        }
    }

    public void sendRejectionEmail(EmailDetailsDto details) throws Exception {
        try {
            LOGGER.info("Envoi d'un email de rejet à {}", details.getRecipient());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(details.getRecipient());
            message.setSubject("Votre compte a été supprimé");
            message.setText("Nous sommes désolés, votre compte a été supprimé.");
            emailSender.send(message);
        } catch (MailException e) {
            LOGGER.error("Erreur lors de l'envoi de l'email de rejet", e);
            throw new Exception("Erreur lors de l'envoi de l'email de rejet : " + e.getMessage());
        }
    }
}