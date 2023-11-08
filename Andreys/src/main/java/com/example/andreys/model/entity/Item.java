package com.example.andreys.model.entity;

import com.example.andreys.model.enums.Gender;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Item(String name, String description, BigDecimal price,
                Category category, Gender gender) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.gender = gender;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Item setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Item setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Item setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
