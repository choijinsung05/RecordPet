package teamyc.recordpet.domain.user.service;

import static teamyc.recordpet.global.exception.ResultCode.DUPLICATE_USER_EMAIL;
import static teamyc.recordpet.global.exception.ResultCode.DUPLICATE_USER_NICKNAME;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamyc.recordpet.domain.user.dto.UserSignupRequest;
import teamyc.recordpet.domain.user.dto.UserSignupResponse;
import teamyc.recordpet.domain.user.entity.User;
import teamyc.recordpet.domain.user.repository.UserRepository;
import teamyc.recordpet.global.exception.GlobalException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignupResponse signup(UserSignupRequest req) {
        // 중복 이메일 체크 (이미 가입된 사람인지 체크)
        checkDuplicateEmail(req);
        // 닉네임 중복 체크
        checkDuplicateNickname(req);
        // 비밀번호 암호화
        String pw = passwordEncoder.encode(req.getPassword());

        User user = req.toEntity(pw);
        userRepository.save(user);

        return UserSignupResponse.fromEntity(user);
    }

    private void checkDuplicateEmail(UserSignupRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new GlobalException(DUPLICATE_USER_EMAIL);
        }
    }

    private void checkDuplicateNickname(UserSignupRequest req) {
        if (userRepository.existsByNickname(req.getNickname())) {
            throw new GlobalException(DUPLICATE_USER_NICKNAME);
        }
    }
}