package com.likebookapp.service;

import com.likebookapp.model.dto.CreatePostDTO;
import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.dto.UserPostsDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.User;

import java.util.List;

public interface PostService {
    void addPost(String content, Mood mood, User user);

    List<UserPostsDTO> findPostsByUser(long id);

    List<PostDTO> findPostsFromOtherUser(long id);

    void removePost(Long id);

    void likePost(Long id);
}
