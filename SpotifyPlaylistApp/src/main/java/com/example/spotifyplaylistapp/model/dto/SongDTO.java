package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Song;

import java.text.DecimalFormat;

public class SongDTO {

    private long id;

    private String performer;

    private String title;

    private String duration;

    public SongDTO() {
    }

    public SongDTO(Song song) {
        this.setId(song.getId());
        this.setPerformer(song.getPerformer());
        this.setTitle(song.getTitle());
        this.setDuration(song.getDuration());

    }

    public long getId() {
        return id;
    }

    public SongDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public SongDTO setDuration(int duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        StringBuilder builder = new StringBuilder();
        builder.append(minutes)
                .append(":");
        if (seconds < 10) {
            builder.append(0);
        }
        builder.append(seconds);
        this.duration = builder.toString();
        return this;
    }
}
