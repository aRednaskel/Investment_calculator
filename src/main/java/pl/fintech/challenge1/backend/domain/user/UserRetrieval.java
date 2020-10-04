package pl.fintech.challenge1.backend.domain.user;

import pl.fintech.challenge1.backend.api.UserDto;

import java.util.List;

public interface UserRetrieval {
    boolean isUsernameAvailable(String username);

    List<UserDto> findAllUsers();

}
