package teamyc.recordpet.domain.weightLog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamyc.recordpet.domain.weightLog.dto.WeightLogResponse;
import teamyc.recordpet.domain.weightLog.repository.WeightLogRepository;
import teamyc.recordpet.global.exception.GlobalException;

import java.util.Date;
import java.util.List;

import static teamyc.recordpet.global.exception.ResultCode.NOT_FOUND_WEIGHT_LOG;

@Service
@RequiredArgsConstructor
public class WeightLogService {

    private final WeightLogRepository weightLogRepository;

    //조회
    public List<WeightLogResponse> WeightLogsFindByDateRange(Long petId, Date startDate, Date endDate) {
        return weightLogRepository.findByPetIdAndDateBetween(petId, startDate, endDate)
                .orElseThrow(() -> new GlobalException(NOT_FOUND_WEIGHT_LOG))
                .stream()
                .map(WeightLogResponse::fromEntity)
                .toList();
    }
}
