package com.matthew633jdi.RA.domain.problem;

import com.matthew633jdi.RA.domain.review.Review;
import com.matthew633jdi.RA.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Problem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String org;
    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "problem")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Problem(String type, Level level, String org, String name, String url, User user) {
        Assert.hasText(type, "Type value must not be null");
        Assert.notNull(level, "Level value must not be null");
        Assert.hasText(org, "Org value must not be null");
        Assert.hasText(name, "Name value must not be null");
        Assert.hasText(url, "Url value must not be null");
        Assert.notNull(user, "User must not be null");
        this.type = type;
        this.level = level;
        this.org = org;
        this.name = name;
        this.url = url;
        this.user = user;
        user.getProblems().add(this);
    }

    public void changeType(String type) {
        Assert.hasText(type, "Type value must not be null");
        this.type = type;
    }

    public void changeLevel(Level level) {
        Assert.notNull(level, "Level value must not be null");
        this.level = level;
    }

    public void changeName(String name) {
        Assert.hasText(name, "Name value must not be null");
        this.name = name;
    }

    public void changeOrg(String org) {
        Assert.hasText(org, "Org value must not be null");
        this.org = org;
    }

    public void changeUrl(String url) {
        Assert.hasText(url, "Url value must not be null");
        this.url = url;
    }
}