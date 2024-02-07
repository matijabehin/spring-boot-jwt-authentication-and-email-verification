package hr.mbehin.springbootjwtauthenticationandemailverification.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final String COMPANY_EMAIL = "info@companyName.com";

    private final JavaMailSender mailSender;

    @Async
    public void send(String to, String email){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Please verify your email");
            helper.setFrom(COMPANY_EMAIL);
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            throw new IllegalStateException("failed to send mail", e);
        }
    }
}
