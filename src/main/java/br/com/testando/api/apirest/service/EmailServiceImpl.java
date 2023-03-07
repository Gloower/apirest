package br.com.testando.api.apirest.service;


import br.com.testando.api.apirest.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public String sendSimpleMail(EmailDetails emailDetails){
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            return "Email enviado com sucesso...";
        }

        catch (Exception e){
            return "Erro ao enviar o email.";
        }
    }

}
