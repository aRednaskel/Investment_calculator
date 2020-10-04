package pl.fintech.challenge1.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fintech.challenge1.backend.api.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserCreator userCreator;
    private final UserRetrieval userRetrieval;


    public void save(UserDto user) {
        userCreator.save(user);
    }


    public boolean isUsernameAvailable(String username) {
        return userRetrieval.isUsernameAvailable(username);
    }

    public List<UserDto> findAll() {
        return userRetrieval.findAllUsers();
    }
}
