package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.Global.GlobalData;
import com.test.Model.Role;
import com.test.Model.User;
import com.test.Repositary.RoleRepo;
import com.test.Repositary.UserRepo;

@Controller
public class LoginController {
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
UserRepo userRepo;
@Autowired
RoleRepo roleRepo;
@GetMapping("/login")
public String login() {
	GlobalData.cart.clear();
return "login";
}

@GetMapping ("/register")
public String registerGet() {
return "register";
}
@PostMapping("/register")
public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
    String password = user.getPassword();
    user.setPassword(bCryptPasswordEncoder.encode(password));
    List<Role> roles = new ArrayList<>();
    roles.add(roleRepo.findById(2).get());
    user.setRoles(roles);
    userRepo.save(user);

    // Check if the registered user is an admin
    if (user.getEmail().equals("admin@gmail.com")) {
        try {
            request.login(user.getEmail(), password);
        } catch (ServletException e) {
            // Handle login exception
        }
        return "redirect:/admin";
    }

    return "redirect:/";
}
}