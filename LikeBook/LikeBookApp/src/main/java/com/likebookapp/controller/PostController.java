package com.likebookapp.controller;

import com.likebookapp.model.dto.CreatePostDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.enums.MoodName;
import com.likebookapp.service.MoodService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    private PostService postService;
    private MoodService moodService;

    private UserService userService;

    @Autowired
    public PostController(PostService postService, MoodService moodService, UserService userService) {
        this.postService = postService;
        this.moodService = moodService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreatePostDTO init() {
        return new CreatePostDTO();
    }

    @GetMapping("/posts/add")
    public String addPost() {

        if (!this.userService.loggedUser()) {
            return "redirect:/";
        }
        return "post-add";
    }

    @PostMapping("/posts/add")
    public String addPost(@Valid CreatePostDTO createPostDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.userService.loggedUser()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || createPostDTO.getMood().isEmpty()) {
            redirectAttributes.addFlashAttribute("createPostDTO", createPostDTO);
            redirectAttributes
                    .addFlashAttribute(redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createPostDTO",
                    bindingResult));
            return "redirect:/posts/add";
        }
        Mood mood = this.moodService.getMood(MoodName.valueOf(createPostDTO.getMood()));
        User user = this.userService.getLoggedUser();
        this.postService.addPost(createPostDTO.getContent(), mood, user);

        return "redirect:/home";
    }

    @GetMapping("posts/remove/{id}")
    public String removePost(@PathVariable Long id) {

        if (!this.userService.loggedUser()) {
            return "redirect:/";
        }
        this.postService.removePost(id);
        return "redirect:/home";
    }

    @GetMapping("posts/like/{id}")
    public String likePost(@PathVariable Long id) {

        if (!this.userService.loggedUser()) {
            return "redirect:/";
        }
        this.postService.likePost(id);
        return "redirect:/home";
    }
}
