package teamyc.recordpet.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public CustomResponse<Void> handleException(GlobalException e) {
        return CustomResponse.error(e.getResultCode());
    }
}