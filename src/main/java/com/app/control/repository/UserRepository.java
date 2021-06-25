package com.app.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.control.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
