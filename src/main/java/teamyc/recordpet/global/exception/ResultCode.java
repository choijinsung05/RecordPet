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
    EMAIL_SEND_FAILED("UE001", "이메일 전송에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_CODE("UE002", "인증 코드가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EMAIL_AUTH("UE003", "인증 정보가 존재하지 않습니다. 다시 시도하세요.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_EMAIL("UE004", "인증 완료되지 않은 email 입니다.", HttpStatus.UNAUTHORIZED),

    // Pet P-
    NOT_FOUND_PET_PROFILE("P001", "조회된 프로필이 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}