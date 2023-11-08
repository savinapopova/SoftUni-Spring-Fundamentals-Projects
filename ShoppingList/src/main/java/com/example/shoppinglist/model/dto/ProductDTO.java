package com.example.shoppinglist.model.dto;

import com.example.shoppinglist.model.entity.Product;

import java.math.BigDecimal;

public class ProductDTO {


    private Long id;

    private String name;

    private BigDecimal price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public ProductDTO() {
    }


    public Long getId() {
        return id;
    }

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
