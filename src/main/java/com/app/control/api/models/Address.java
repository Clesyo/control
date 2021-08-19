package com.app.control.api.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@ApiModelProperty("CEP")
	@NotEmpty(message = "Campo CEP é obrigatório.")
	private String zipCode;

	@Column(nullable = false)
	@NotEmpty(message = "Campo Endereço é obrigatório.")
	@ApiModelProperty("Logradouro do endereço")
	private String andress;

	@Column(name = "number_house", nullable = false)
	@NotNull(message = "Campo Número é obrigatório.")
	@ApiModelProperty("Número da residencia")
	private Integer numberHouse;
	
	@ApiModelProperty("Complemento do endereço")
	private String complement;

	@Column(nullable = false)
	@NotEmpty(message = "Campo Bairro é obrigatório")
	@ApiModelProperty("Bairro do endereço")
	private String district;

	@Column(nullable = false)
	@NotEmpty(message = "Campo Cidade é obrigatório")
	@ApiModelProperty("Cidade do endereço")
	private String city;

	@Column(nullable = false, columnDefinition = "varchar(2)")
	@NotEmpty(message = "Campo UF é obrigatório")
	@ApiModelProperty("UF do endereço")
	private String uf;

	@ManyToOne
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;

}
