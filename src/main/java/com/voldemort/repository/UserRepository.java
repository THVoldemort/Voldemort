package com.voldemort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voldemort.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
