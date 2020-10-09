package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.RegistrationDTO;
import pl.fintech.challenge1.backend.controller.dto.UserDTO;
import pl.fintech.challenge1.backend.controller.mapper.UserMapper;
import pl.fintech.challenge1.backend.domain.user.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createNewUser (@Valid @RequestBody RegistrationDTO registrationDTO) {
        return userMapper.mapUserToDTO(userService.save(userMapper.mapDTOToUser(registrationDTO)));
    }


    @GetMapping
    public @ResponseBody Iterable<UserDTO> getAllUsers() {
        return userService.findAll().stream()
                .map(userMapper::mapUserToDTO)
                .collect(Collectors.toList());
    }
}
