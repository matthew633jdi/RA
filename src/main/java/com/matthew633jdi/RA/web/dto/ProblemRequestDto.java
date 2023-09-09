package com.matthew633jdi.RA.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
