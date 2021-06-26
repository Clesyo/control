package com.app.control.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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
@Table(name = "payments")
public class Payment {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean actived;

	@JsonIgnoreProperties
	@OneToMany(mappedBy = "payment")
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

	public boolean isActived() {
		return this.actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

}
