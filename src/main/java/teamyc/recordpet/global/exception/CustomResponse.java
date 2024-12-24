package teamyc.recordpet.global.exception;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CustomResponse<T> implements Serializable {

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private T data;

    public static <T> CustomResponse<T> success(T data) {
        return CustomResponse.<T>builder()
            .httpStatus(ResultCode.SUCCESS.getHttpStatus())
            .code(ResultCode.SUCCESS.getCode())
            .message(ResultCode.SUCCESS.getMessage())
            .data(data)
            .build();
    }

    public static <T> CustomResponse<T> created(T data) {
        return CustomResponse.<T>builder()
            .httpStatus(ResultCode.CREATED.getHttpStatus())
            .code(ResultCode.CREATED.getCode())
            .message(ResultCode.CREATED.getMessage())
            .data(data)
            .build();
    }

    public static <T> CustomResponse<T> error(ResultCode resultCode) {
        return CustomResponse.<T>builder()
            .httpStatus(resultCode.getHttpStatus())
            .code(resultCode.getCode())
            .message(resultCode.getMessage())
            .build();
    }
}