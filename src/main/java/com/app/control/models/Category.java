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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;
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

	public long getParent() {
		return this.parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * public Set<Product> getProducts() { return this.products; }
	 * 
	 * public void setProducts(Set<Product> products) { this.products = products; }
	 */

}
