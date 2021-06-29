package com.app.control.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(columnDefinition = "varchar(36)", unique = true, nullable = false)
	private String token;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@ApiModelProperty(value = "Numero de CNPJ")
	@Column(unique = true, nullable = false)
	private String identity;
	private String telephone;
	@Column(nullable = false)
	private String cellphone;
	@Column(nullable = false)
	private String zipCode;
	@Column(nullable = false)
	private String andress;
	@Column(nullable = false)
	private int numberHouse;
	private String complement;
	@Column(nullable = false)
	private String district;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false, columnDefinition = "char(2)")
	private char uf;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Category> categories;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Ingredient> ingredients;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Product> products;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Order> orders;
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "company")
	private List<Combo> combos;

	@LastModifiedDate
	@Column(name = "created_at", columnDefinition = "timestamp")
	private Timestamp createdAt;
	@CreatedDate
	@Column(name = "updated_at", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Timestamp updatedAt;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAndress() {
		return this.andress;
	}

	public void setAndress(String andress) {
		this.andress = andress;
	}

	public int getNumberHouse() {
		return this.numberHouse;
	}

	public void setNumberHouse(int numberHouse) {
		this.numberHouse = numberHouse;
	}

	public String getComplement() {
		return this.complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public char getUf() {
		return this.uf;
	}

	public void setUf(char uf) {
		this.uf = uf;
	}

	/*
	 * public Set<Category> getCategories() { return this.categories; }
	 * 
	 * public void setCategories(Set<Category> categories) { this.categories =
	 * categories; }
	 * 
	 * public Set<Ingredient> getIngredients() { return this.ingredients; }
	 * 
	 * public void setIngredients(Set<Ingredient> ingredients) { this.ingredients =
	 * ingredients; }
	 */

}
