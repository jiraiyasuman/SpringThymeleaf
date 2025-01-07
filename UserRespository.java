package com.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springthymeleaf.user.User;

public interface UserRespository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
