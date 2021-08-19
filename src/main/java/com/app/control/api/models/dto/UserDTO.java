package com.app.control.api.models.dto;

import com.app.control.api.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String password;

	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public static UserDTO convertToDto(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail());
	}
	
}
