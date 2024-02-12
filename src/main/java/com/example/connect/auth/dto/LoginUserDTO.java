package com.example.connect.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDTO {
    @Email
    @NotNull
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    @Size(min=6, max=32)
    public String password;
}
