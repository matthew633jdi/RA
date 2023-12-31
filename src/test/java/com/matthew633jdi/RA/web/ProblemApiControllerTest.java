package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.domain.problem.Level;
import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.service.problem.ProblemService;
import com.matthew633jdi.RA.web.dto.problem.ProblemRequestDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemSaveRequestDto;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProblemApiControllerTest {

    @InjectMocks
    ProblemApiController problemApiController;

    @Mock
    ProblemService problemService;

    @Mock
    MockHttpSession session;

    @Test
    @DisplayName("Problem 생성")
    void problem_생성() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UserResponseDto user = new UserResponseDto();

        session.setAttribute("user", user);

        when(problemService.register(any(ProblemSaveRequestDto.class), anyLong())).thenReturn(1L);

        ProblemSaveRequestDto dto = ProblemSaveRequestDto.builder()
                .userId(1L)
                .level("beginner")
                .name("name test")
                .url("http://boj.com/22")
                .type("brute force")
                .org("BOJ")
                .build();

        Long savedId = problemApiController.save(dto);

        assertThat(savedId).isEqualTo(1L);
    }

    @Test
    @DisplayName("Problem 모두 조회")
    void problem_모두조회() throws Exception {
        User userA = User.builder()
                .name("userA")
                .email("email")
                .password("pwd")
                .build();
        Problem prb1 = Problem.builder()
                .user(userA)
                .level(Level.ADVANCED)
                .name("name test")
                .url("http://boj.com/22")
                .type("brute force")
                .org("BOJ")
                .build();
        Problem prb2 = Problem.builder()
                .user(userA)
                .level(Level.INTERMEDIATE)
                .name("name test1")
                .url("http://boj.com/23")
                .type("Graph")
                .org("BOJ")
                .build();
        ProblemResponseDto dto1 = new ProblemResponseDto(prb1);
        ProblemResponseDto dto2 = new ProblemResponseDto(prb2);

        List<ProblemResponseDto> list = List.of(dto1, dto2);

        ProblemRequestDto requestDto = new ProblemRequestDto();

        when(problemService.findProblem(requestDto)).thenReturn(list);

        List<ProblemResponseDto> result = problemApiController.findByRequest(requestDto);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo(prb1.getName());
        assertThat(result.get(1).getName()).isEqualTo(prb2.getName());

    }

}