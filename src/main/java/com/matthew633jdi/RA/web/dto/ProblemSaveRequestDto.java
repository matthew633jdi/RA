package com.matthew633jdi.RA.web.dto;

import com.matthew633jdi.RA.domain.problem.Level;
import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProblemSaveRequestDto {

    private String name;
    private String type;
    private String level;
    private String url;
    private String org;
    private Long userId;

    @Builder
    public ProblemSaveRequestDto(String name, String type, String level, String url, String org, Long userId) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.url = url;
        this.org = org;
        this.userId = userId;
    }

}
