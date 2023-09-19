package com.matthew633jdi.RA.service.problem;

import com.matthew633jdi.RA.domain.problem.Level;
import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.problem.ProblemRepository;
import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.domain.user.UserRepository;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemSaveRequestDto;
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

    // BLOG TODO
    // - Spring boot SLice Test (각 레이어별 테스트)
    // - Mockito
    // - DTO와 Entity 분리와 기준
    @Test
    @DisplayName("문제등록")
    void problem_등록() {
        // given
        User user = User.builder().build();
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
        Long registeredId = service.register(dto, 1L);
        // then
        verify(service, times(1)).register(dto, 1L);
    }
    
    @Test
    @DisplayName("문제 조회")
    void problem_조회() {
        // given
        Problem prb = Problem.builder()
                .name("b1")
                .type("Graph Search")
                .level(Level.ADVANCED)
                .url("http://boj.com/2")
                .org("BOJ")
                .user(User.builder().build()).build();
        when(problemRepository.findById(1L)).thenReturn(Optional.of(prb));
        // when
        ProblemResponseDto response = service.getProblemById(1L);
        // then
        verify(service, times(1)).getProblemById(1L);
    }
    

}