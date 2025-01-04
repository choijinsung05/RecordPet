package teamyc.recordpet.global.mail.service;

import static teamyc.recordpet.global.exception.ResultCode.NOT_FOUND_EMAIL_AUTH;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamyc.recordpet.global.exception.GlobalException;
import teamyc.recordpet.global.mail.entity.EmailAuth;
import teamyc.recordpet.global.mail.repository.EmailAuthRepository;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final EmailAuthRepository emailAuthRepository;

    public EmailAuth findById(String email) {
        return emailAuthRepository.findById(email)
            .orElseThrow(() -> new GlobalException(NOT_FOUND_EMAIL_AUTH));
    }

    public boolean hasMail(String email) {
        return emailAuthRepository.existsById(email);
    }

    public void save(EmailAuth emailAuth) {
        emailAuthRepository.save(emailAuth);
    }

    public void delete(String email) {
        emailAuthRepository.deleteById(email);
    }
}