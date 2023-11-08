package com.example.andreys.model.dto;

import com.example.andreys.model.entity.Item;

import java.math.BigDecimal;

public class ItemDTO {

   private Long id;

   private String name;

   private BigDecimal price;

   private String description;

   private String category;

   private String gender;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.category = item.getCategory().getName().name();
        this.gender = item.getGender().name();
    }

    public ItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public ItemDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ItemDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ItemDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
