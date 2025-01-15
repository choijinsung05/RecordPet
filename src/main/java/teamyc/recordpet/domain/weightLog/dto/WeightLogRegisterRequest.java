package teamyc.recordpet.domain.weightLog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class WeightLogRegisterRequest {

    @NotNull(message = "애완동물 식별자는 필수 요소입니다.")
    private final Long petId;

    @NotNull(message = "몸무게는 필수 입력값입니다.")
    @Min(value = 0, message = "몸무게는 0보다 큰 값을 입력해야 합니다.")
    private final Double weight;

    private final Date date;

    public WeightLogRegisterRequest(Long petId, Double weight, Date date) {
        this.petId = petId;
        this.weight = weight;
        this.date = date != null ? date : new Date();
    }
}
