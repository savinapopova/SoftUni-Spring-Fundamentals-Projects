package com.likebookapp.model.dto;

import com.likebookapp.model.enums.MoodName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePostDTO {

    @NotBlank
    private String content;

    @NotBlank
    private String mood;

    public CreatePostDTO(String content, String mood) {
        this.content = content;
        this.mood = mood;
    }

    public CreatePostDTO(String mood) {
        this.mood = mood;
    }

    public CreatePostDTO() {
    }

    public String getContent() {
        return content;
    }

    public CreatePostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public CreatePostDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }
}
