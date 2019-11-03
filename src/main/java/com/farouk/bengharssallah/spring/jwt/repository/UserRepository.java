package com.farouk.bengharssallah.spring.jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farouk.bengharssallah.spring.jwt.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
}