package com.matthew633jdi.RA.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String pwd;

    @Builder
    public UserRequestDto(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
