package teamyc.recordpet.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultCode {
    // Success
    SUCCESS("Success", "정상 처리 되었습니다.", HttpStatus.OK),

    // User U-
    DUPLICATE_USER_EMAIL("U001", "이미 가입된 이메일 입니다.", HttpStatus.CONFLICT);

    // Pet P-

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}