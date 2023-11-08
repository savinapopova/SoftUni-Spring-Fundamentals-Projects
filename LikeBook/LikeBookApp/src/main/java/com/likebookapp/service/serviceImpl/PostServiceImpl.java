package com.likebookapp.service.serviceImpl;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.dto.UserPostsDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public void addPost(String content, Mood mood, User user) {
        Post post = new Post();
        post.setContent(content)
                .setMood(mood)
                .setUser(user);
        this.postRepository.save(post);
    }

    @Override
    public List<UserPostsDTO> findPostsByUser(long id) {
        List<Post> posts = this.postRepository.findAllByUserId(id);
         return posts.stream().map(UserPostsDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostsFromOtherUser(long id) {
        List<Post> posts = this.postRepository.findAllByUserIdNot(id);
        return posts.stream().map(PostDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void removePost(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public void likePost(Long id) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new NoSuchElementException();
        }
        Post post = optionalPost.get();
        User user = this.userService.getLoggedUser();

        post.getUserLikes().add(user);
        postRepository.save(post);
    }
}
