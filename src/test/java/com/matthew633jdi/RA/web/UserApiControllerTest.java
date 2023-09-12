package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.service.user.UserService;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {

    @InjectMocks
    UserApiController userApiController;

    @Mock
    UserService userService;

    @Test
    @DisplayName("회원 가입")
    void signup() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.register(any(UserSaveRequestDto.class))).thenReturn(2L);
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .name("name")
                .email("email")
                .pwd("pwd")
                .build();

        Long value = userApiController.save(requestDto);

        assertThat(value).isEqualTo(2L);
    }

    @Test
    @DisplayName("회원 조회")
    void user_조회() {
        Long id = 3L;
        String userName = "name test";
        User user = User.builder()
                .name(userName)
                .password("pwd")
                .email("email")
                .build();
        UserResponseDto responseDto = new UserResponseDto(user);

        when(userService.findById(id)).thenReturn(responseDto);

        UserResponseDto byId = userApiController.findById(id);

        assertThat(byId.getName()).isEqualTo(userName);
    }
    

}