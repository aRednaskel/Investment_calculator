package pl.fintech.challenge1.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fintech.challenge1.backend.domain.dao.User;
import pl.fintech.challenge1.backend.domain.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.exception.UsernameAlreadyExistsException;
import pl.fintech.challenge1.backend.persistence.UserRepository;
import pl.fintech.challenge1.backend.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User saveUser (RegistrationDTO registrationDTO){
        try{
            User user = User.builder()
                    .username(registrationDTO.getUsername())
//                    .password(bCryptPasswordEncoder.encode(registrationDTO.getPassword()))
                    .password(registrationDTO.getPassword())
                    .build();

            return userRepository.save(user);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+registrationDTO.getUsername()+"' already exists");
        }
    }
}
