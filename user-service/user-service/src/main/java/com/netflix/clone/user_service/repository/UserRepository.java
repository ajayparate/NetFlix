package com.netflix.clone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Optional<User> findByEmail(String email);

    // Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
