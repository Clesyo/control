package com.app.control.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.control.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);
}
