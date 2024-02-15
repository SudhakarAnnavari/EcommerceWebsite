package com.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.Model.CustomUserDetails;
import com.test.Model.User;
import com.test.Repositary.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService {
@Autowired
UserRepo userRepo;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Optional<User> userByEmail = userRepo.findUserByEmail(email);
	userByEmail.orElseThrow(()-> new UsernameNotFoundException("no user"));
	return userByEmail.map(CustomUserDetails::new).get();
}

}