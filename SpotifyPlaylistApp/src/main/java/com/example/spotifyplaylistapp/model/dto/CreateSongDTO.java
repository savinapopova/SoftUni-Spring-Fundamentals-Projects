package com.example.spotifyplaylistapp.model.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateSongDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String performer;

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;

    @Positive
    private int duration;

    @NotBlank
    private String style;

    public CreateSongDTO(String performer, String title, LocalDate releaseDate, int duration, String style) {
        this.performer = performer;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.style = style;
    }

    public CreateSongDTO() {
    }

    public String getPerformer() {
        return performer;
    }

    public CreateSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public CreateSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public CreateSongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getStyle() {
        return style;
    }

    public CreateSongDTO setStyle(String style) {
        this.style = style;
        return this;
    }
}
