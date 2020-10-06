package pl.fintech.challenge1.backend.controller.mapper;

import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.controller.dto.UserDto;
import pl.fintech.challenge1.backend.domain.user.User;

@Component
class UserMapperImpl implements UserMapper{
    @Override
    public User mapDTOToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    @Override
    public UserDto mapUserToDTO(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword()).build();
    }
}
