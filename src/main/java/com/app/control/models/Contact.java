package com.app.control.models;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "contacts")
public class Contact {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "contact")
	private List<Address> addresses;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "contact")
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
