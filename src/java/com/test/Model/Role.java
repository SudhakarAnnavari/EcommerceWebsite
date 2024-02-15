package com.test.Model;

import java.util.List;

@javax.persistence.Entity
public class Role {
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Integer id;
	@javax.persistence.Column (nullable = false, unique = true)
	private String name;
	@javax.persistence.ManyToMany(mappedBy = "roles")
	private List<User> users;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	

}
