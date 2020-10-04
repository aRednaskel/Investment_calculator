package pl.fintech.challenge1.backend.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.api.UserDto;
import pl.fintech.challenge1.backend.domain.model.User;
import pl.fintech.challenge1.backend.domain.user.UserRetrieval;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserRetrievalImpl implements UserRetrieval {

    private final UserRepository userRepository;

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserRetrievalImpl::mapUserToDto)
                .collect(Collectors.toList());
    }

    static UserDto mapUserToDto(User user) {
        return new UserDto(user.getUsername(), user.getPassword());
    }
}
