package com.test.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Model.User;

public interface UserRepo  extends JpaRepository<User,Integer> {
	
	Optional<User>  findUserByEmail(String email);

}
