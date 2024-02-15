package com.test.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Model.Role;



public interface RoleRepo extends JpaRepository<Role, Integer> {


}
