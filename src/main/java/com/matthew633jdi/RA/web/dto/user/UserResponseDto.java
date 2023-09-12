package com.matthew633jdi.RA.web.dto.user;

import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.web.dto.problem.ProblemResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private List<ProblemResponseDto> problems;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.problems = user.getProblems().stream().map(ProblemResponseDto::new).collect(Collectors.toList());
    }
}
