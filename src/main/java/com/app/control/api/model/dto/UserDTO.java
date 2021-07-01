package com.app.control.api.model.dto;

import com.app.control.models.User;

import lombok.Getter;

@Getter
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String password;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public static UserDTO convertToDto(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail());
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
