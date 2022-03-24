package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	// SELECT* FROM user WHERE username=1?; 	
	Optional<User> findByUsername(String username);
}

// jpa naming전략 
// SELECT* FROM user WHERE username=? AND password=?랑 동일	
	/*User findByUsernameAndPassword(String username, String password);
	*/
