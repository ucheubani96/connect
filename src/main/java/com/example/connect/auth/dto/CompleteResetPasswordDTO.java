package com.example.connect.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteResetPasswordDTO {
    @NotNull
    @NotBlank
    @Size(min=6, max=32)
    public String password;

    @NotNull
    @NotBlank
    @Size(min=6, max=32)
    public String retypePassword;
}
