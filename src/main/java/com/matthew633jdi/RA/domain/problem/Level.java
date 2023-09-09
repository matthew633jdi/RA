package com.matthew633jdi.RA.domain.problem;

import java.util.Arrays;

public enum Level {
    BEGINNER("beginner"), INTERMEDIATE("intermediate"), ADVANCED("advanced");

    private String value;

    Level(String value) {
        this.value = value;
    }

    public static Level findByCode(String code) {
        return Arrays.stream(Level.values())
                .filter(level -> level.hasLevel(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Wrong level code"));
    }

    private boolean hasLevel(String code) {
        return Arrays.stream(Level.values()).anyMatch(level -> level.value.equals(code));
    }

    public String getLevelValue() {
        return value;
    }
}
