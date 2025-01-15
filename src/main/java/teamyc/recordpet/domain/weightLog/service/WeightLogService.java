package teamyc.recordpet.domain.weightLog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamyc.recordpet.domain.pet.entity.Pet;
import teamyc.recordpet.domain.pet.repository.PetRepository;
import teamyc.recordpet.domain.weightLog.dto.WeightLogRegisterRequest;
import teamyc.recordpet.domain.weightLog.dto.WeightLogResponse;
import teamyc.recordpet.domain.weightLog.entity.WeightLog;
import teamyc.recordpet.domain.weightLog.repository.WeightLogRepository;
import teamyc.recordpet.global.exception.GlobalException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static teamyc.recordpet.global.exception.ResultCode.NOT_EXIST_PET;
import static teamyc.recordpet.global.exception.ResultCode.NOT_FOUND_WEIGHT_LOG;

@Service
@RequiredArgsConstructor
public class WeightLogService {

    private final WeightLogRepository weightLogRepository;
    private final PetRepository petRepository;

    //조회
    public List<WeightLogResponse> WeightLogsFindByDateRange(Long petId, LocalDate startDate, LocalDate endDate) {
        if (!petRepository.existsById(petId)) {
            throw new GlobalException(NOT_EXIST_PET);
        }
        if(startDate == null){
            startDate = getMonthsAgoDate(6);
        }
        if(endDate == null){
            endDate = LocalDate.now();
        }
        return weightLogRepository.findByPetIdAndDateBetween(petId, startDate, endDate)
                .orElseThrow(() -> new GlobalException(NOT_FOUND_WEIGHT_LOG))
                .stream()
                .map(WeightLogResponse::fromEntity)
                .toList();
    }

    //등록
    public Long registerWeightLog(WeightLogRegisterRequest request){
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new GlobalException(NOT_EXIST_PET));

        WeightLog weightLog = WeightLog.builder()
                .pet(pet)
                .weight(request.getWeight())
                .date(request.getDate())
                .build();

        WeightLog savedWeightLog = weightLogRepository.save(weightLog);

        return savedWeightLog.getId();
    }


    private LocalDate getMonthsAgoDate(int months) {
        return LocalDate.now().minusMonths(months);
    }
}
