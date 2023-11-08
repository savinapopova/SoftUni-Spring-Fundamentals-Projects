package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;

public class UserPostsDTO {

    private Long id;

    private String mood;

    private int likes;

    private String content;

    public UserPostsDTO() {
    }

    public UserPostsDTO(Post post) {
        this.id = post.getId();
        this.mood = String.valueOf(post.getMood().getName());
        this.likes = post.getUserLikes().size();
        this.content = post.getContent();
    }



    public String getMood() {
        return mood;
    }

    public UserPostsDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public UserPostsDTO setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public String getContent() {
        return content;
    }

    public UserPostsDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserPostsDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
