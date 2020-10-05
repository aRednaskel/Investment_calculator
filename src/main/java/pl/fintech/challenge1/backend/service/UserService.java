package pl.fintech.challenge1.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fintech.challenge1.backend.domain.dao.User;
import pl.fintech.challenge1.backend.domain.dto.RegistrationDTO;

@Service
public interface UserService {

    @Transactional
    public User saveUser (RegistrationDTO registrationDTO);
}
