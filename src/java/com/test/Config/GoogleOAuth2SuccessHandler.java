package com.test.Config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.test.Model.Role;

//Replace all occurrences of com.nimbusds.oauth2.sdk.Role with com.test.Model.Role

import com.test.Model.User;
import com.test.Repositary.RoleRepo;
import com.test.Repositary.UserRepo;

@Component
public class GoogleOAuth2SuccessHandler  implements AuthenticationSuccessHandler{
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(userRepo.findUserByEmail(email).isPresent()) 
		{
		} 
		else {
		User user = new User();
		user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
		user.setLastName (token.getPrincipal().getAttributes().get("family_name").toString());
		user.setEmail(email);
		Role role = roleRepo.findById(2).orElse(null); // or provide a default role if not found
		if (role != null) {
		    user.setRoles(Collections.singletonList(role));
		} else {
		    throw new RuntimeException("Role not found with ID: 2");
		}
        userRepo.save(user);
		}
		
		redirectStrategy.sendRedirect(request, response, "/");
		
		
		
	}
	
	
	

}
