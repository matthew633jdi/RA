package com.matthew633jdi.RA.domain;

import com.matthew633jdi.RA.domain.problem.Level;
import com.matthew633jdi.RA.domain.problem.Problem;
import com.matthew633jdi.RA.domain.review.Review;
import com.matthew633jdi.RA.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
public class JpaTest {
    //    @PersistenceContext
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("User 생성 테스트")
    void user_생성() {
        // given
        User userA = new User("userA");
        em.persist(userA);
        // when
        User findUser = em.find(User.class, userA.getId());
        // then
        assertThat(findUser.getName()).isEqualTo(userA.getName());
    }
    
    @Test
    @DisplayName("Problem 생성 테스트")
    void problem_생성() {
        // given
        User userB = new User("userB");
        em.persist(userB);

        Problem problem = Problem.builder()
                .name("BOJ 1")
                .url("http://boj.com/1")
                .type("Brute Force")
                .user(userB)
                .org("BOJ")
                .level(Level.BEGINNER)
                .build();
        em.persist(problem);
        // when
        List<Problem> problems = em.createQuery("select p from Problem p " +
                "where user.id = :userId", Problem.class).setParameter("userId", userB.getId()).getResultList();
        // then

        assertThat(problems).hasSize(1);
        assertThat(problems.get(0).getId()).isEqualTo(problem.getId());
    }

    @Test
    @DisplayName("Review 생성 테스트")
    void review_생성() {
        // given
        User userC = new User("userC");
        em.persist(userC);

        Problem problem = Problem.builder()
                .name("BOJ 1")
                .url("http://boj.com/1")
                .type("Brute Force")
                .user(userC)
                .org("BOJ")
                .level(Level.BEGINNER)
                .build();
        em.persist(problem);

        Review r1 = Review.builder()
                .date(LocalDate.now().minusDays(7))
                .problem(problem)
                .solved(true)
                .build();

        Review r2 = Review.builder()
                .date(LocalDate.now().minusDays(14))
                .problem(problem)
                .solved(false)
                .build();

        em.persist(r1);
        em.persist(r2);
        // when
        List<Review> reviews = em.createQuery("select r from Review r " +
                "where problem.user.id = :userId", Review.class).setParameter("userId", userC.getId()).getResultList();
        // then
        assertThat(reviews.size()).isEqualTo(2L);
        assertThat(reviews.get(0).getSolved()).isEqualTo(r1.getSolved());
    }

    
}
