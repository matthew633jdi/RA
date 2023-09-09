package com.matthew633jdi.RA.domain.problem;

import com.matthew633jdi.RA.web.dto.ProblemRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // 동적 쿼리가 필요한 듯... -> queryDsl 적용 필요해 보임
    default List<Problem> findByProblems(ProblemRequestDto problemRequestDto) {
        return null;
    }

    List<Problem> findByUrl(String url);
}
