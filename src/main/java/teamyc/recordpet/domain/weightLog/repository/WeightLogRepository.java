package teamyc.recordpet.domain.weightLog.repository;

import org.springframework.data.repository.RepositoryDefinition;
import teamyc.recordpet.domain.weightLog.entity.WeightLog;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = WeightLog.class, idClass = Long.class)
public interface WeightLogRepository {

    Optional<List<WeightLog>> findByPetIdAndDateBetween(Long petId, Date startDate, Date endDate);

    WeightLog save(WeightLog weightLog);
}
