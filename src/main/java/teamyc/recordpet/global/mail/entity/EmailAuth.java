package teamyc.recordpet.global.mail.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "auth", timeToLive = 300)
public class EmailAuth {

    @Id
    private String email;
    private String code;
    private boolean isChecked = false;

    @Builder
    public EmailAuth(String email, String code, boolean isChecked) {
        this.email = email;
        this.code = code;
        this.isChecked = isChecked;
    }
}
