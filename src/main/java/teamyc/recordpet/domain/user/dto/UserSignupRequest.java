package teamyc.recordpet.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import teamyc.recordpet.domain.user.entity.Role;
import teamyc.recordpet.domain.user.entity.User;

@Getter
@AllArgsConstructor
public class UserSignupRequest {

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private final String email;
    @NotBlank
    private final String nickname;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[a-zA-Z]).{1,16}$\n")
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
