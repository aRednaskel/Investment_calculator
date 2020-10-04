package pl.fintech.challenge1.backend.domain.user;

import pl.fintech.challenge1.backend.api.UserDto;

public interface UserCreator {
    void save(UserDto userDto);
}
