package com.app.control.api.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ApiModelProperty("Nome do produto")
	@NotEmpty(message = "Campo nome deve ser preenchido.")
	@Column(nullable = false)
	private String name;

	@ApiModelProperty("Preço do produto")
	@NotNull(message = "O preço não deve ser zero.")
	@Column(columnDefinition = "double(10,3) default 0.00", nullable = false)
	private Double price;
	
	@ApiModelProperty("Descrição do produto")
	private String description;

	@ApiModelProperty("Identifica se o produto está no menu.")
	@Column(name = "menu", columnDefinition = "tinyint(1) default 0")
	private boolean menuOp;

	@ApiModelProperty("Indentifica o tipo de produto: Produto Final, Acompanhamento, Sobremessa, Suco, Combo")
	@Column(columnDefinition = "char(1) default 'P'")
	private String type;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ApiModelProperty("Imagem do produto")
	private String image;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToMany(mappedBy = "product")
	private List<Menu> menus;

}
