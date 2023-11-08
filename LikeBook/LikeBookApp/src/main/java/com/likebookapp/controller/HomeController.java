package com.likebookapp.controller;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.dto.UserPostsDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private LoggedUser loggedUser;

    private UserService userService;

    private PostService postService;

    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService,
                          PostService postService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index() {
        if (this.userService.loggedUser()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!this.userService.loggedUser()) {
            return "redirect:/";
        }

        List<UserPostsDTO> userPosts = this.postService
                .findPostsByUser(loggedUser.getId());
        List<PostDTO> otherPosts = this.postService
                .findPostsFromOtherUser(loggedUser.getId());

        model.addAttribute("userPosts", userPosts);
        model.addAttribute("otherPosts", otherPosts);
        return "home";
    }
}
