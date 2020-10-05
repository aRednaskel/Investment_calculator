package pl.fintech.challenge1.backend.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationDTO {

    @Email(message = "Wprowadź poprawny email")
    @NotBlank(message = "Wprowadź email")
    private String username;

    @Size(min = 6, message = "Hasło musi mieć conajmniej 6 znaków")
    private String password;

    private String confirmPassword;
}