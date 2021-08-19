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
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ApiModelProperty("Nome da forma de pagamento")
	@NotEmpty(message = "Campo Nome é obrigatório.")
	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "default 1")
	private boolean actived;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "payment")
	private List<Order> orders;

}
