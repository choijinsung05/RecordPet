package teamyc.recordpet.domain.weightLog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamyc.recordpet.domain.weightLog.dto.WeightLogResponse;
import teamyc.recordpet.domain.weightLog.service.WeightLogService;
import teamyc.recordpet.global.exception.CustomResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController("/api/v1/weight")
@RequiredArgsConstructor
public class WeightLogController {

    private final WeightLogService weightLogService;

    @GetMapping
    public CustomResponse<List<WeightLogResponse>> findWeightLogByPetId(
            @RequestParam Long petId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        List<WeightLogResponse> res = weightLogService.WeightLogsFindByDateRange(petId, startDate, endDate);
        return CustomResponse.success(res);
    }
}
