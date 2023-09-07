package com.matthew633jdi.RA.domain.review;

import com.matthew633jdi.RA.domain.problem.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Boolean solved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prd_id")
    private Problem problem;

    @Builder
    public Review(LocalDate date, Boolean solved, Problem problem) {
        Assert.notNull(date, "Date value must not be null");
        Assert.notNull(solved, "Solved value must not be null");
        Assert.notNull(problem, "Problem must not be null");
        this.date = date;
        this.solved = solved;
        this.problem = problem;
        problem.getReviews().add(this);
    }

    public void updateReview(LocalDate date, Boolean solved) {
        this.date = date;
        this.solved = solved;
    }
}
