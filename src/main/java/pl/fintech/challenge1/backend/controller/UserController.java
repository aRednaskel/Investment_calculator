package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.controller.mapper.UserMapper;
import pl.fintech.challenge1.backend.domain.user.User;
import pl.fintech.challenge1.backend.domain.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @PostMapping(path="/register")
    public @ResponseBody
    User addNewUser (@RequestBody RegistrationDTO registrationDTO) {
        //todo: this should be hashed by bcrypt but for now it will do
        return userService.saveUser(userMapper.mapDTOToUser(registrationDTO));
    }
}
