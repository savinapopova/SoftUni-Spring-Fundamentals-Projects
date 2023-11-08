package com.example.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    private int duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(optional = false)
    private Style style;

    public Song(String performer, String title, int duration, LocalDate releaseDate, Style style) {
        this.performer = performer;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.style = style;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public Song setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public Song setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Song setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Song setStyle(Style style) {
        this.style = style;
        return this;
    }
}
