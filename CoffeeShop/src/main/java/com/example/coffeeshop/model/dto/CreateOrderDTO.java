package com.example.coffeeshop.model.dto;

import jakarta.validation.constraints.*;
import jdk.jfr.Category;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateOrderDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderTime;

    @NotBlank
    private String category;

    @NotBlank
    @Size(min = 5)
    private String description;

    public CreateOrderDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public CreateOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CreateOrderDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
