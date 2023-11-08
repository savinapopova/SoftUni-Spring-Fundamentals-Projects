package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.enums.CategoryName;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryName name;

    @Column(name = "needed_time", nullable = false)
    private int neededTime;

    public Category(CategoryName name) {
        this.name = name;
        setNeededTime(name);
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryName getName() {
        return name;
    }

    public Category setName(CategoryName name) {
        this.name = name;
        return this;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(CategoryName name) {
        int time =
        switch (name) {
            case DRINK -> 1;
            case COFFEE -> 2;
            case OTHER -> 5;
            case CAKE -> 10;
        };
        this.neededTime = time;
        return this;
    }
}
