package person.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    public JavaMailSender emailSender;

    public EmailSenderService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

        public void sendSimpleEmail(String toAddress, String subject, String message) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(toAddress);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            emailSender.send(simpleMailMessage);
        }
}