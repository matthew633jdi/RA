package com.matthew633jdi.RA.service.user;

import com.matthew633jdi.RA.domain.problem.Level;
import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.problem.ProblemRepository;
import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.domain.user.UserRepository;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    ProblemRepository problemRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    UserService service;

    @Test
    @DisplayName("회원등록")
    void user_등록() {
        // given
        User user = User.builder().name("a").email("email").password("pwd").build();
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .name("a")
                .email("email")
                .pwd("pwd")
                .build();

        // when
        Long registeredId = service.register(requestDto);

        // then
        assertThat(registeredId).isEqualTo(user.getId());
        verify(service, times(1)).register(requestDto);
    }

    @Test
    @DisplayName("회원 조회")
    void user_조회() {
        // given
        User userA = User.builder().build();
        Problem prb = Problem.builder()
                .name("b1")
                .type("Graph Search")
                .level(Level.ADVANCED)
                .url("http://boj.com/2")
                .org("BOJ")
                .user(userA)
                .build();
        Problem prb2 = Problem.builder()
                .name("b2")
                .type("Graph Search")
                .level(Level.INTERMEDIATE)
                .url("http://boj.com/8")
                .org("BOJ")
                .user(userA)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(userA));

        // when
        UserResponseDto responseDto = service.findById(1L);

        // then
        assertThat(responseDto.getProblems().get(0).getName()).isEqualTo(userA.getProblems().get(0).getName());

        verify(service, times(1)).findById(1L);
    }

}