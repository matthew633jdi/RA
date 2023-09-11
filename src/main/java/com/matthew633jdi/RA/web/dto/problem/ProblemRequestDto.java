package com.matthew633jdi.RA.web.dto.problem;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor
public class ProblemRequestDto {
    private String type;
    private String level;
    private String name;
    private String url;
    private String org;

    @Builder
    public ProblemRequestDto(String type, String level, String name, String url, String org) {
        this.type = type;
        this.level = level;
        this.name = name;
        this.url = url;
        this.org = org;
    }

    public boolean isEmpty() {
        return !StringUtils.hasText(type) && !StringUtils.hasText(level) && !StringUtils.hasText(name) && !StringUtils.hasText(url) && !StringUtils.hasText(org);
    }
}
