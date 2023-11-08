package com.example.shoppinglist.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateProductDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private LocalDateTime before;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String category;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String name, String description, LocalDateTime before, BigDecimal price, String category) {
        this.name = name;
        this.description = description;
        this.before = before;
        this.price = price;
        this.category = category;
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

    public LocalDateTime getBefore() {
        return before;
    }

    public CreateProductDTO setBefore(LocalDateTime before) {
        this.before = before;
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
}
