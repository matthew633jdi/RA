package com.matthew633jdi.RA.domain.user;

import com.matthew633jdi.RA.domain.problem.Problem;
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
@Table(name = "users")  // h2 2.1.212부터 User 예약어
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private final List<Problem> problems = new ArrayList<>();

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void changeUserInfo(String name, String email) {
        Assert.hasText(name, "Name value must not be null");
        Assert.hasText(email, "Email value must not be null");
        this.name = name;
        this.email = email;
    }
}
