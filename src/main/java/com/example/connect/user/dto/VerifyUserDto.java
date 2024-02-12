package com.example.connect.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyUserDto {
    @NotNull
    @NotBlank
    @Size(min=30, max=40)
    public String token;
}
