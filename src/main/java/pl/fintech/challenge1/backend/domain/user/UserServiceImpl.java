package pl.fintech.challenge1.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public User save(User user) {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            log.info("Created user with email {}", user.getEmail());
            return userRepository.save(user);
        }
        log.error("User with email {} already exists", user.getEmail());
        throw new EmailAlreadyExistsException("Username '" + user.getEmail() + "' already exists");
    }
}
