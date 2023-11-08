package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.CreateUserDTO;
import com.example.spotifyplaylistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRegisterController {

    private UserService userService;


   @Autowired
   public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public CreateUserDTO init() {
        return new CreateUserDTO();
    }

    @GetMapping("/register")
    public String register() {

        if (this.userService.isLogged()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid CreateUserDTO createUserDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (this.userService.isLogged()) {
            return "redirect:/home";
        }


        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createUserDTO", createUserDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createUserDTO",
                    bindingResult);
            return "redirect:/register";

        }
        if (!this.userService.checkAvailable(createUserDTO)){
            redirectAttributes.addFlashAttribute("createUserDTO", createUserDTO);
            redirectAttributes
                    .addFlashAttribute("alreadyExists", true);
            return "redirect:/register";

        }
        if (!this.userService.passwordsMatch(createUserDTO.getPassword(), createUserDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("createUserDTO", createUserDTO);
            redirectAttributes
                    .addFlashAttribute("passwordsMatch", false);
            return "redirect:/register";
        }
        this.userService.register(createUserDTO);
        return "redirect:login";
    }
}
