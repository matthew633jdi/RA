package com.matthew633jdi.RA.service.problem;

import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.problem.ProblemRepository;
import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.domain.user.UserRepository;
import com.matthew633jdi.RA.web.dto.ProblemSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProblemServiceTest {

    @Mock
    ProblemRepository problemRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    ProblemService service;

    @Test
    @DisplayName("문제등록")
    void problem_등록() {
        // given
        User user = new User("a");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ProblemSaveRequestDto dto = ProblemSaveRequestDto.builder()
                .name("problem1")
                .url("http://boj.com/1")
                .level("beginner")
                .type("brute force")
                .org("BOJ")
                .userId(1L)
                .build();


        when(problemRepository.save(any(Problem.class))).then(AdditionalAnswers.returnsFirstArg());

        // when
        Long registeredId = service.register(dto);
        // then
        verify(service, times(1)).register(dto);
    }

}