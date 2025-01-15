package teamyc.recordpet.domain.weightLog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

import java.time.LocalDate;

    @NotNull(message = "애완동물 식별자는 필수 요소입니다.")
    private final Long petId;

    public WeightLogRegisterRequest(Long petId, Double weight, LocalDate date) {
        this.petId = petId;
        this.weight = weight;
        this.date = date != null ? date : LocalDate.now();
    }
}
