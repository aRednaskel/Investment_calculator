package pl.fintech.challenge1.backend.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String email;
}
