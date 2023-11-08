package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodName;
import jakarta.persistence.*;


@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private MoodName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood(MoodName name) {
        this.name = name;
    }

    public Mood() {
    }

    public Long getId() {
        return id;
    }

    public Mood setId(Long id) {
        this.id = id;
        return this;
    }

    public MoodName getName() {
        return name;
    }

    public Mood setName(MoodName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
