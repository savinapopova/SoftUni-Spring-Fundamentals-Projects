package com.example.musicdb.model.entity;

import com.example.musicdb.model.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int copies;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;


    private String producer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne(optional = false)
    private Artist artist;

    @ManyToOne(optional = false)
    @JoinColumn(name = "added_from", referencedColumnName = "id")
    private User addedFrom;

    public Album(String name, String imageUrl, String description, int copies, BigDecimal price,
                 LocalDate releaseDate,
                 String producer, Genre genre, Artist artist, User addedFrom) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.copies = copies;
        this.price = price;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.genre = genre;
        this.artist = artist;
        this.addedFrom = addedFrom;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public Album setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Album setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Album setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public Album setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Album setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Album setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public Album setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public Album setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public User getAddedFrom() {
        return addedFrom;
    }

    public Album setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }
}
