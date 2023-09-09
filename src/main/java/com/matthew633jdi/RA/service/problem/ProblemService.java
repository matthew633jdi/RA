package com.matthew633jdi.RA.service.problem;

import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.problem.ProblemRepository;
import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.domain.user.UserRepository;
import com.matthew633jdi.RA.web.dto.ProblemRequestDto;
import com.matthew633jdi.RA.web.dto.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.ProblemSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long register(ProblemSaveRequestDto problemSaveRequestDto) {
        // 중복체크
        validateDuplicateProblem(problemSaveRequestDto);

        // 조회
        User user = userRepository.findById(problemSaveRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Wrong User."));

        // 생성
        Problem problem = Problem.createProblem(problemSaveRequestDto, user);
        return problemRepository.save(problem).getId();
    }

    public ProblemResponseDto getProblemById(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
        return new ProblemResponseDto(problem);
    }

    public List<ProblemResponseDto> findAll() {
        return problemRepository.findAll().stream().map(ProblemResponseDto::new).collect(Collectors.toList());
    }

    public List<ProblemResponseDto> findProblemsBy(ProblemRequestDto problemRequestDto) {
        return problemRepository.findByProblems(problemRequestDto).stream().map(ProblemResponseDto::new).collect(Collectors.toList());
    }

    private void validateDuplicateProblem(ProblemSaveRequestDto problemSaveRequestDto) {
        List<Problem> findProblems = problemRepository.findByUrl(problemSaveRequestDto.getUrl());
        if (!findProblems.isEmpty()) {
            throw new IllegalStateException("이미 등록된 문제입니다.");
        }
    }
}
