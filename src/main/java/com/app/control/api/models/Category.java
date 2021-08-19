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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ApiModelProperty("Nome da categoria")
	@NotEmpty(message = "Nome da categoria não pode ser vazio.")
	@Column(nullable = false)
	private String name;
	@ApiModelProperty(value = "Referencia da categoria principal.")
	private long parent;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "category")
	private List<Menu> menus;

}
