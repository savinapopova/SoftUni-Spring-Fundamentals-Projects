package com.example.musicdb.model.dto;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.enums.Genre;
import com.example.musicdb.model.enums.SingerName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumDTO {

    private Long id;

    private String name;

    private SingerName artist;

    private int copies;

    private String imageUrl;

    private Genre genre;

    private BigDecimal price;

    private LocalDate releaseDate;

    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.copies = album.getCopies();
        this.artist = album.getArtist().getName();
        this.imageUrl = album.getImageUrl();
        this.genre = album.getGenre();
        this.price = album.getPrice();
        this.releaseDate = album.getReleaseDate();
    }

    public AlbumDTO() {
    }

    public Long getId() {
        return id;
    }

    public AlbumDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumDTO setName(String name) {
        this.name = name;
        return this;
    }

    public SingerName getArtist() {
        return artist;
    }

    public AlbumDTO setArtist(SingerName artist) {
        this.artist = artist;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AlbumDTO setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public AlbumDTO setCopies(int copies) {
        this.copies = copies;
        return this;
    }
}
