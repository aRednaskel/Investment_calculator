package pl.fintech.challenge1.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;

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
