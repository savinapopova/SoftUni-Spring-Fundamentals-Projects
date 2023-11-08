package com.example.gira.model.entity;

import com.example.gira.model.enums.ClassificationName;
import jakarta.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificationName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Classification(ClassificationName name) {
        this.name = name;
    }

    public Classification() {
    }

    public Long getId() {
        return id;
    }

    public Classification setId(Long id) {
        this.id = id;
        return this;
    }

    public ClassificationName getName() {
        return name;
    }

    public Classification setName(ClassificationName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Classification setDescription(String description) {
        this.description = description;
        return this;
    }
}
