package hr.mbehin.springbootjwtauthenticationandemailverification.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;

    public String build(String username, String link){
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("link", link);
        return templateEngine.process("mailTemplate", context);
    }
}
