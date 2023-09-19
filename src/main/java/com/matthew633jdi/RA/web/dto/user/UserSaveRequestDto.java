package com.matthew633jdi.RA.web.dto.user;

import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.web.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Builder;
import lombok.Data;

@Data
public class UserSaveRequestDto {

    @NotBlank(message = "The email address is required.")
    @Email(message = "The email address is invalid", flags = {Flag.CASE_INSENSITIVE})
    private String email;

    @NotBlank(message = "The name is required.")
    private String name;

    @ValidPassword
    private String pwd;

    @Builder
    public UserSaveRequestDto(String email, String name, String pwd) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(pwd)
                .build();
    }
}
