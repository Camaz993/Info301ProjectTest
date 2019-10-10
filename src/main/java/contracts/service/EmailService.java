package contracts.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	private final JavaMailSender sender;

    @Autowired
    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void send(String emailTo, String subject, String body) {
        MimeMessagePreparator message = newMessage -> {
            newMessage.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(emailTo)
            );
            newMessage.setFrom("contractmanagementsystem301@gmail.com");
            newMessage.setSubject(subject);
            newMessage.setText(body);
        };

        this.sender.send(message);
    }
}}
