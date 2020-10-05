package pl.fintech.challenge1.backend.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;

@Service
public interface UserService {

    @Transactional
    public User saveUser (RegistrationDTO registrationDTO);
}
