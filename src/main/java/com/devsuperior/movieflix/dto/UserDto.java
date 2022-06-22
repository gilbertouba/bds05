package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.User;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID =1L;	
	
	private Long id;
	
	private String email;
	private String name;
	

	public UserDto() {}
	
	public UserDto(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDto(User user) {
	    this.id =user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNname() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
