package pl.fintech.challenge1.backend.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
public interface UserService {
    User saveUser(User user);
}
