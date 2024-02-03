package com.gl.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.springboot.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> getUserByUsername(String username);
	
}
