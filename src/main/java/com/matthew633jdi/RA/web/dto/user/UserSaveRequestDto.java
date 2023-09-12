package com.matthew633jdi.RA.web.dto.user;

import com.matthew633jdi.RA.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String email;
    private String name;
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
