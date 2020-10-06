package pl.fintech.challenge1.backend.controller.mapper;

import pl.fintech.challenge1.backend.controller.dto.UserDto;
import pl.fintech.challenge1.backend.domain.user.User;

public interface UserMapper {
    User mapDTOToUser(UserDto userDto);

    UserDto mapUserToDTO(User user);
}
