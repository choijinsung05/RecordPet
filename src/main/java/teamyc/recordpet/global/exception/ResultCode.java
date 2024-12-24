package teamyc.recordpet.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultCode {
    // Success
    SUCCESS("Success", "정상 처리 되었습니다.", HttpStatus.OK),
    CREATED("Created", "등록에 성공했습니다.", HttpStatus.CREATED),

    // User U-
    DUPLICATE_USER_EMAIL("U001", "이미 가입된 이메일 입니다.", HttpStatus.CONFLICT),
    DUPLICATE_USER_NICKNAME("U002", "중복된 닉네임 입니다.", HttpStatus.CONFLICT),

    // Pet P-
    NOT_FOUND_PET_PROFILE("P001","조회된 프로필이 없습니다.",HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}