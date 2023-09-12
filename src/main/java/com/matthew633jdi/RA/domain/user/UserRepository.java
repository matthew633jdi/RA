package com.matthew633jdi.RA.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndAndPassword(String email, String password);
}
