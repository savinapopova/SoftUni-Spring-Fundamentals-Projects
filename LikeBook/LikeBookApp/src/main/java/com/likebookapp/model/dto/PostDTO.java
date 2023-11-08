package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Post;

public class PostDTO {

    private String username;

    private Long id;

    private String mood;

    private int likes;

    private String content;

    public PostDTO() {
    }


    public PostDTO(Post post) {
        this.id = post.getId();
        this.mood = String.valueOf(post.getMood().getName());
        this.likes = post.getUserLikes().size();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
    }



    public String getMood() {
        return mood;
    }

    public PostDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public PostDTO setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PostDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public PostDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
