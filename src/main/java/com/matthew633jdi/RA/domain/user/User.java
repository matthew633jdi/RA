package com.matthew633jdi.RA.domain.user;

import com.matthew633jdi.RA.domain.problem.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")  // h2 2.1.212부터 User 예약어
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private final List<Problem> problems = new ArrayList<>();
    public User(String name) {
        this.name = name;
    }

    public void changeUserName(String name) {
        Assert.hasText(name, "Name value must not be null");
        this.name = name;
    }
}
