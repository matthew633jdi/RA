package com.matthew633jdi.RA.domain.user;

import com.matthew633jdi.RA.domain.problem.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("user 생성")
    void user_생성() {
        // given
        User userA = new User("userA");
        userRepository.save(userA);
        // when
        User findUesr = userRepository.findById(userA.getId()).orElseThrow(() -> new IllegalArgumentException("Wrong user id"));
        // then
        assertThat(findUesr.getName()).isEqualTo(userA.getName());
    }


}