package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Style(StyleName name) {
        this.name = name;
    }

    public Style() {
    }

    public Long getId() {
        return id;
    }

    public Style setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleName getName() {
        return name;
    }

    public Style setName(StyleName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
