package com.app.control.api.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Getter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Nome de usuário deve ser preenchido.")
	private String name;
	@Column(unique = true, nullable = false)
	@Email
	private String email;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Senha deve ser preenchido.")
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Company> companies;

	private boolean admin;
}