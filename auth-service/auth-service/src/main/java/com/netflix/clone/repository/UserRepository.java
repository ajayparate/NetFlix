package com.netflix.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // User findByEmail(String email);
    // boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
