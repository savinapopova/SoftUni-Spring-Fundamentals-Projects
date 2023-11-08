package com.example.musicdb.model.entity;

import com.example.musicdb.model.enums.SingerName;
import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SingerName name;

    @Column(name = "career_information", columnDefinition = "TEXT")
    private String careerInformation;

    public Artist(SingerName name) {
        this.name = name;
    }

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public Artist setId(Long id) {
        this.id = id;
        return this;
    }

    public SingerName getName() {
        return name;
    }

    public Artist setName(SingerName name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public Artist setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
