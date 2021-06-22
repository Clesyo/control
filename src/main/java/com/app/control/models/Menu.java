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
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private boolean typeA;
    private boolean typeB;
    private boolean typeD;
    private boolean typeE;
    private boolean typeP;
    private double price;
    @OneToMany(mappedBy = "menu")
    private Set<Product> products;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
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

    public Set<Product> getProduct() {
        return this.products;
    }

    public void setProduct(Set<Product> products) {
        this.products = products;
    }

}
