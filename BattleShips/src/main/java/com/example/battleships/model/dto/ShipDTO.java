package com.example.battleships.model.dto;

import com.example.battleships.model.entity.Ship;

public class ShipDTO {

    private Long id;

    private String name;

    private long power;

    private long health;

    public ShipDTO() {
    }

    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.power = ship.getPower();
        this.health = ship.getHealth();
    }


    public Long getId() {
        return id;
    }

    public ShipDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }
}
