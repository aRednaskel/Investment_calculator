package pl.fintech.challenge1.backend.domain.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository {

    User save(User user);

}