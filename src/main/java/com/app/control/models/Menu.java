package com.app.control.models;

import java.sql.Timestamp;
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

@Entity
@Table(name = "menus")
public class Menu {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Column(name = "type_a")
	private boolean typeA;

	@Column(name = "type_b")
	private boolean typeB;

	@Column(name = "type_d")
	private boolean typeD;

	@Column(name = "type_e")
	private boolean typeE;

	@Column(name = "type_p")
	private boolean typeP;

	@Column(nullable = false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isTypeA() {
		return this.typeA;
	}

	public void setTypeA(boolean typeA) {
		this.typeA = typeA;
	}

	public boolean isTypeB() {
		return this.typeB;
	}

	public void setTypeB(boolean typeB) {
		this.typeB = typeB;
	}

	public boolean isTypeD() {
		return this.typeD;
	}

	public void setTypeD(boolean typeD) {
		this.typeD = typeD;
	}

	public boolean isTypeE() {
		return this.typeE;
	}

	public void setTypeE(boolean typeE) {
		this.typeE = typeE;
	}

	public boolean isTypeP() {
		return this.typeP;
	}

	public void setTypeP(boolean typeP) {
		this.typeP = typeP;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
