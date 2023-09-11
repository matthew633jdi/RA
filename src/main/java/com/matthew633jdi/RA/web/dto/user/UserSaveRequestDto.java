package com.matthew633jdi.RA.web.dto.user;

import com.matthew633jdi.RA.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String name;

    public UserSaveRequestDto(String name) {
        this.name = name;
    }

    public User toEntity() {
        return new User(name);
    }
}
