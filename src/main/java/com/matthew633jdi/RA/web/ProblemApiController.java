package com.matthew633jdi.RA.web;

import com.matthew633jdi.RA.service.problem.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.matthew633jdi.RA.web.dto.problem.ProblemRequestDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import com.matthew633jdi.RA.web.dto.problem.ProblemSaveRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProblemApiController {

    private final ProblemService problemService;

    @PostMapping("/problems")
    public Long save(@RequestBody ProblemSaveRequestDto problemSaveRequestDto) {
        return problemService.register(problemSaveRequestDto);
    }

    @GetMapping("/problems/{id}")
    public ProblemResponseDto findById(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }


    @GetMapping("/problems")
    public List<ProblemResponseDto> findByRequest(ProblemRequestDto problemRequestDto) {
        return problemService.findProblem(problemRequestDto);
    }
}
