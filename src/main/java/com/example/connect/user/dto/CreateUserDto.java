package com.example.connect.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDto {
    @NotNull
    @NotBlank
    @Size(min=3, max=40)
    public String username;

    @Email
    @NotNull
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    @Size(min=6, max=32)
    public String password;

    @NotEmpty(message = "dp field must not be empty")
    @Size(min=1, max=100)
    public String dp;
}
