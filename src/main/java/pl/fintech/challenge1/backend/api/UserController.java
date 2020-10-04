package pl.fintech.challenge1.backend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.domain.model.User;
import pl.fintech.challenge1.backend.domain.user.UserFacade;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    UserDto addNewUser (@RequestBody UserDto userDto) {
        //todo: this should be hashed by bcrypt but for now it will do
        userFacade.save(userDto);
        return userDto;
    }

    @GetMapping("/{username}")
    public boolean isUsernameAvailable(@PathVariable String username) {
        return userFacade.isUsernameAvailable(username);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userFacade.findAll().stream()
                .map(UserController::mapUserToDto)
                .collect(Collectors.toList());
    }

    static User mapUserToDto(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
}
