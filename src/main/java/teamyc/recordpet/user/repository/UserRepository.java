package teamyc.recordpet.user.repository;

import org.springframework.data.repository.RepositoryDefinition;
import teamyc.recordpet.user.entity.User;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {
    User save(User user);
    Boolean existsByEmail(String email);
}