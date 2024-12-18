package teamyc.recordpet.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import teamyc.recordpet.domain.user.entity.User;
import teamyc.recordpet.domain.user.entity.Role;

@Getter
@AllArgsConstructor
public class UserSignupRequest {

    private final String email;
    private final String nickname;
    private final String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
            .email(this.email)
            .nickname(this.nickname)
            .password(encodedPassword)
            .role(Role.MEMBER)
            .build();
    }
}
