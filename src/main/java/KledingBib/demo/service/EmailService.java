package KledingBib.demo.service;

import KledingBib.demo.models.Email;
import KledingBib.demo.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;



@Service

public class EmailService implements EmailRepository {

@Autowired
    private JavaMailSender javaMailSender;

  @Value("emailService.emailAddress=${ormenojavier452@gmail.com:default-email@gmail.com}\n")
    private String sender;
   // private EmailRepository emailService;


    public String sendMail(Email email) {
        try {
            MailMessage mailMessage = new SimpleMailMessage() ;


            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getReceiver());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());

            javaMailSender.send((SimpleMailMessage) mailMessage);

//            Email email1 = new Email("ormenojavier452@gmail.com", "Er is een nieuw verzonden");
//            this.emailService.sendMail(email);
            return "Mail successfully sended...";
        }


        catch (Exception e) {
            return "Sending Mail in Error";
        }
    }
//
//    public String sendMailTwo(Email email) {
//        try {
//            MailMessage mailMessage = new SimpleMailMessage() ;
//
//
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(email.getReceiver());
//            mailMessage.setText(email.getMessage());
//            mailMessage.setSubject(email.getSubject());
//
//            javaMailSender.send((SimpleMailMessage) mailMessage);
//
//            Email email1 = new Email("ormenojavier452@gmail.com", "Er is een nieuw verzonden");
//            this.emailService.sendMailTwo(email);
//            return "Mail successfully sended...";
//        }
//
//
//        catch (Exception e) {
//            return "Sending Mail in Error";
//        }
//    }
//
//


    public String sendWithAttachment(Email email) {
        MimeMessage myMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper myMessageHelper;

        try {
            myMessageHelper = new MimeMessageHelper(myMessage, true);
            myMessageHelper.setFrom(sender);
            myMessageHelper.setTo(email.getReceiver());
            myMessageHelper.setText(email.getMessage());
            myMessageHelper.setSubject(email.getSubject());


            FileSystemResource file = new FileSystemResource(new File(email.getAttachment()));
            myMessageHelper.addAttachment(file.getFilename(), file);


            javaMailSender.send(myMessage);
            return "Mail successfully sended";
        } catch (MessagingException e) {
            return "Sending Mail in Error!";
        }
    }
}

