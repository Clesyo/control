package com.app.control.models;

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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
public class Product {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "double(10,3) default 0.00", nullable = false)
	private double price;
	private String description;

	@Column(columnDefinition = "char(1)")
	private char size;

	@Column(name = "portion_size", columnDefinition = "char(1) default 'P'")
	private char portionSize;

	@Column(name = "menu", columnDefinition = "tinyint(1) default 0")
	private boolean menuOp;

	@Column(columnDefinition = "char(1) default 'P'")
	private char type;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	private String image;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany(mappedBy = "products")
	private List<Combo> combos;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "product")
	private List<Menu> menus;

	@ManyToMany
	@JoinTable(name = "ingredient_product", joinColumns = {
			@JoinColumn(name = "ingredient_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private List<Ingredient> ingredients;

	@ManyToMany(mappedBy = "products")
	private List<Order> orders;

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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getSize() {
		return this.size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	public char getPortionSize() {
		return this.portionSize;
	}

	public void setPortionSize(char portionSize) {
		this.portionSize = portionSize;
	}

	public boolean isMenu() {
		return this.menuOp;
	}

	public void setMenu(boolean menuOp) {
		this.menuOp = menuOp;
	}

	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Combo> getCombos() {
		return combos;
	}

	public void setCombos(List<Combo> combos) {
		this.combos = combos;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	/*
	 * public Set<Combo> getCombo() { return this.combos; }
	 * 
	 * public void setCombo(Set<Combo> combos) { this.combos = combos; }
	 */

}
