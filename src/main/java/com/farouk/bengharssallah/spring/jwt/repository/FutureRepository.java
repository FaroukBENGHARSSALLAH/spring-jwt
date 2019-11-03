package com.farouk.bengharssallah.spring.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farouk.bengharssallah.spring.jwt.domain.Future;

@Repository
public interface FutureRepository extends JpaRepository<Future, Long> {
	
	Future findByTicker(String ticker);
	
}