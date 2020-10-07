package pl.fintech.challenge1.backend.controller.mapper;

import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.controller.dto.UserDTO;
import pl.fintech.challenge1.backend.domain.user.User;

public interface UserMapper {
    User mapDTOToUser(RegistrationDTO registrationRequestDTO);
    UserDTO mapUserToDTO(User user);
}
