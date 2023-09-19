package com.matthew633jdi.RA.web.dto.user;

import com.matthew633jdi.RA.web.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class UserRequestDto {
    @NotBlank(message = "The email address is required.")
    @Email(message = "The email address is invalid", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @ValidPassword
    private String pwd;

    @Builder
    public UserRequestDto(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
