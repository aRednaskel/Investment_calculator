package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.controller.dto.UserDto;
import pl.fintech.challenge1.backend.controller.mapper.UserMapper;
import pl.fintech.challenge1.backend.domain.user.User;
import pl.fintech.challenge1.backend.domain.user.UserService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> addNewUser (@RequestBody UserDto userDto) {
        //todo: this should be hashed by bcrypt but for now it will do
        userService.save(userMapper.mapDTOToUser(userDto));
        return ResponseEntity.status(201)
                .body(userDto);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<UserDto> getAllUsers() {
        return userService.findAll().stream()
                .map(userMapper::mapUserToDTO)
                .collect(Collectors.toList());
    }
}
