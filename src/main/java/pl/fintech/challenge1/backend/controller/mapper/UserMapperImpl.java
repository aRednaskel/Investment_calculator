package pl.fintech.challenge1.backend.controller.mapper;

import org.springframework.stereotype.Component;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.domain.user.User;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User mapDTOToUser(RegistrationDTO registrationRequestDTO) {
        return User.builder()
                .email(registrationRequestDTO.getEmail())
                .password(registrationRequestDTO.getPassword())
                .build();
    }
}
