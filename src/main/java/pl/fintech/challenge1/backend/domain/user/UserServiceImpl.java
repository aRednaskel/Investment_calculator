package pl.fintech.challenge1.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyExistsException("Username '" + user.getEmail() + "' already exists");
        }
    }

    @Override
    public List<User> findAll () {
        return null;
    }
}
