package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.controller.mapper.UserMapper;
import pl.fintech.challenge1.backend.domain.user.User;
import pl.fintech.challenge1.backend.domain.user.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser (@Valid @RequestBody RegistrationDTO registrationDTO) {
        return userService.saveUser(userMapper.mapDTOToUser(registrationDTO));
    }
}
