package com.example.coffeeshop.model.dto;

import com.example.coffeeshop.model.entity.User;

public class UserDTO {

    private String username;

    private int ordersCount;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.ordersCount = user.getOrders().size();
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public UserDTO setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
        return this;
    }
}
