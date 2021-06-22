package com.app.control.models;

import java.sql.Timestamp;
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
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(columnDefinition = "double(10,3)")
    private double price;
    @Column(nullable = true)
    private String description;
    @Column(columnDefinition = "char(1)", nullable = true)
    private char size;
    @Column(name = "portion_size", columnDefinition = "char(1) default 'P'", nullable = true)
    private char portionSize;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean menu;
    @Column(columnDefinition = "char(1) default 'P'")
    private char type;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(nullable = true)
    private String image;
    @ManyToMany(mappedBy = "products")
    private Set<Combo> combos;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menuID; 
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "ingredient_product",
        joinColumns = {@JoinColumn(name = "ingredient_id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Ingredient> ingredients;
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders;

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
        return this.menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
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

    public Set<Combo> getCombo() {
        return this.combos;
    }

    public void setCombo(Set<Combo> combos) {
        this.combos = combos;
    }

}
