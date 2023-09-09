package com.matthew633jdi.RA.web.dto;

import com.matthew633jdi.RA.domain.problem.Problem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProblemResponseDto {

    private LocalDate reviewDate;
    private Boolean solved;
    private String type;
    private String level;
    private String name;
    private String url;
    private String org;


    public ProblemResponseDto(Problem problem) {
        this.type = problem.getType();
        this.level = problem.getLevel().getLevelValue();
        this.name = problem.getName();
        this.url = problem.getUrl();
        this.org = problem.getOrg();
        this.reviewDate = problem.getLatestReview().getDate();
        this.solved = problem.getLatestReview().getSolved();
    }
}
