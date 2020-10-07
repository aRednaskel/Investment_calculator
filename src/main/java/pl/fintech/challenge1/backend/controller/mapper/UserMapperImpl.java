package pl.fintech.challenge1.backend.controller.mapper;

import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.controller.dto.UserDTO;
import pl.fintech.challenge1.backend.domain.user.User;

@Component
class UserMapperImpl implements UserMapper {
    @Override
    public User mapDTOToUser(RegistrationDTO registrationRequestDTO) {
        return User.builder()
                .email(registrationRequestDTO.getEmail())
                .password(registrationRequestDTO.getPassword())
                .build();
    }

    @Override
    public UserDTO mapUserToDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail()).build();
    }
}
