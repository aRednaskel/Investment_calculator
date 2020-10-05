package pl.fintech.challenge1.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fintech.challenge1.backend.domain.user.User;
import pl.fintech.challenge1.backend.domain.user.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String example() {
        return "Hello FinTech!";
    }

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String username, @RequestParam String password) {
        //todo: this should be hashed by bcrypt but for now it will do
        User user = User.builder().username(username).password(password).build();
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
