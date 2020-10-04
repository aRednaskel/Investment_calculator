package pl.fintech.challenge1.backend.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fintech.challenge1.backend.domain.model.User;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}