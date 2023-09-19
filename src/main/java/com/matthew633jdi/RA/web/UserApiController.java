package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.service.user.UserService;
import com.matthew633jdi.RA.web.dto.user.UserRequestDto;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserSaveRequestDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/users")
    public Long save(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.register(userSaveRequestDto);
    }

    @GetMapping("/users")
    public UserResponseDto login(@ModelAttribute UserRequestDto userRequestDto) {
        UserResponseDto user = userService.findUser(userRequestDto);
        httpSession.setAttribute("user", user);
        return user;
    }

    @GetMapping("/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
