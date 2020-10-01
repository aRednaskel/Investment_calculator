package pl.fintech.challenge1.backend.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.fintech.challenge1.backend.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {


}