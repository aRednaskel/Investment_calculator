package pl.fintech.challenge1.backend.domain.user;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();
}
