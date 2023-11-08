package com.example.andreys.model.dto;


import com.example.andreys.model.enums.CategoryName;
import com.example.andreys.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductDTO {

    @NotBlank
    @Size(min = 3)
    private String name;


    @NotBlank
    @Size(min = 4)
    private String description;


    @NotNull
    @Positive
    private BigDecimal price;


    @NotBlank
    private String category;

    @NotBlank
    private String gender;

    public CreateProductDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CreateProductDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CreateProductDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
