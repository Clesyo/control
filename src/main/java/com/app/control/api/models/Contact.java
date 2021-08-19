package com.app.control.api.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@ApiModelProperty("Nome do contato")
	@NotEmpty(message = "Compo Nome é obrigatório.")
	private String name;

	@Column(nullable = false, unique = true)
	@ApiModelProperty("Email para contato")
	@NotEmpty(message = "Compo Email é obrigatório.")
	private String email;
	
	@Column(nullable = false)
	@ApiModelProperty("Celular para contato")
	@NotEmpty(message = "Compo Celular é obrigatório.")
	private String cell;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "contact")
	private List<Address> addresses;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "contact")
	private List<Order> orders;

	
}
