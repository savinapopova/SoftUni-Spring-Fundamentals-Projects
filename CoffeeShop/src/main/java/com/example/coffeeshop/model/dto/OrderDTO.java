package com.example.coffeeshop.model.dto;

import com.example.coffeeshop.model.entity.Order;

import java.math.BigDecimal;

public class OrderDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.price = order.getPrice();
    }

    public String getName() {
        return name;
    }

    public OrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
