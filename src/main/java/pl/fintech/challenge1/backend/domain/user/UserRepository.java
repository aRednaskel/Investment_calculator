package pl.fintech.challenge1.backend.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends CrudRepository<User, Integer> {
    User save(User user);
    Optional<User> findByEmail(String email);
}