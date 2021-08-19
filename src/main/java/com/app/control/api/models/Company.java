package com.app.control.api.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ApiModelProperty("Token de indentificação da empresa.")
	@NotEmpty(message = "Token não pode ser vazio.")
	@NotNull(message = "Token não pode ser NULL")
	@Column(columnDefinition = "varchar(255)", unique = true, nullable = false)
	private String token;
	@Column(nullable = false)
	@ApiModelProperty("Razão Social")
	@NotEmpty(message = "Campo Razão Social não pode ser vazio.")
	private String name;
	@Email(message = "Informe um email válido.")
	@NotEmpty(message = "Campo Email não pode ser vazio.")
	@Column(unique = true, nullable = false)
	private String email;
	@ApiModelProperty(value = "Numero de CNPJ")
	@NotEmpty(message = "Campo CNPJ não poder ser vazio.")
	@Column(unique = true, nullable = false)
	private String identity;
	private String telephone;
	@NotEmpty(message = "Campo Celular não poder ser vazio.")
	@Column(nullable = false)
	private String cellphone;
	@Column(nullable = false)
	@NotEmpty(message = "Campo CEP não poder ser vazio.")
	private String zipCode;
	@NotEmpty(message = "Campo Endereço não poder ser vazio.")
	@Column(nullable = false)
	private String andress;
	@NotNull(message = "Campo Número não poder ser vazio.")
	@Column(nullable = false)
	private int numberHouse;
	private String complement;
	@NotEmpty(message = "Campo Bairro não poder ser vazio.")
	@Column(nullable = false)
	private String district;
	@NotEmpty(message = "Campo Cidade não poder ser vazio.")
	@Column(nullable = false)
	private String city;
	@NotEmpty(message = "Campo UF não poder ser vazio.")
	@Column(nullable = false, columnDefinition = "varchar(2)")
	private String uf;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Category> categories;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Product> products;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Order> orders;

}
