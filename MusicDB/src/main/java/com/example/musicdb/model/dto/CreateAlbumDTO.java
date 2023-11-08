package com.example.musicdb.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateAlbumDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String imageUrl;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    @Min(10)
    private int copies;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;


    private String producer;

    @NotBlank
    private String artist;

    @NotBlank
    private String genre;

    @NotBlank
    @Size(min = 5)
    private String description;

    public CreateAlbumDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateAlbumDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateAlbumDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateAlbumDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public CreateAlbumDTO setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public CreateAlbumDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public CreateAlbumDTO setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public CreateAlbumDTO setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public CreateAlbumDTO setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateAlbumDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
