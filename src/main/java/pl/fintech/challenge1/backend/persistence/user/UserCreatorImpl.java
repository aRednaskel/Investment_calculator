package pl.fintech.challenge1.backend.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.api.UserDto;
import pl.fintech.challenge1.backend.domain.model.User;
import pl.fintech.challenge1.backend.domain.user.UserCreator;

@Service
@RequiredArgsConstructor
class UserCreatorImpl implements UserCreator {

    private final UserRepository userRepository;

    @Override
    public void save(UserDto userDto) {
        userRepository.save(User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build());
    }
}
