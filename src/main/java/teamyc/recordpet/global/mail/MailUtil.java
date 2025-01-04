package teamyc.recordpet.global.mail;

import static teamyc.recordpet.global.exception.ResultCode.EMAIL_SEND_FAILED;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import teamyc.recordpet.global.exception.GlobalException;
import teamyc.recordpet.global.mail.entity.EmailAuth;
import teamyc.recordpet.global.mail.service.EmailAuthService;

@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender mailSender;
    private final EmailAuthService emailAuthService;

    @Value("${spring.mail.username}")
    private final String email;

    @Value("${spring.mail.url.auth}")
    private final String authEmailLink;

    public String createAuthCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void sendMessage(String to) {
        try {
            String code = createAuthCode();
            MimeMessage message = createMessage(to, code);

            if (emailAuthService.hasMail(to)) {
                emailAuthService.delete(to);
            }
            EmailAuth emailAuth = EmailAuth.builder().email(to).code(code).build();

            emailAuthService.save(emailAuth);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new GlobalException(EMAIL_SEND_FAILED);
        }
    }

    private MimeMessage createMessage(String to, String code)
        throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(email);
        message.addRecipients(RecipientType.TO, to);
        message.setSubject("[RecordPet] 이메일 인증");
        message.setText(authEmailLink + "?email=" + email + "&authCode=" + code);

        return message;
    }
}
