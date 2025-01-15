package teamyc.recordpet.domain.weightLog.dto;

import lombok.Builder;
import lombok.Getter;
import teamyc.recordpet.domain.weightLog.entity.WeightLog;

import java.time.LocalDate;

@Builder
@Getter
public class WeightLogResponse {
    private LocalDate date;
    private Double weight;

    public static WeightLogResponse fromEntity(WeightLog weightLog){
        return WeightLogResponse.builder()
                .date(weightLog.getDate())
                .weight(weightLog.getWeight())
                .build();
    }
}
