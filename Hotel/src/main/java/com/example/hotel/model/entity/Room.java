package com.example.hotel.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@MappedSuperclass
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private int capacity;

    private int floor;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Room setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Room setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public Room setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Room setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public Room setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }
}
