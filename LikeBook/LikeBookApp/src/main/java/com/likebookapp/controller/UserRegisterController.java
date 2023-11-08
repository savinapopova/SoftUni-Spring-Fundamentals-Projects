package com.likebookapp.controller;

import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.service.UserService;
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
    public UserRegisterDTO init() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (this.userService.loggedUser()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (this.userService.loggedUser()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !userService.checkAvailable(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
                    bindingResult);

            return "redirect:/register";
        }

        if (!userService.passwordsMatch(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("passwordsNotMatch", true);

            return "redirect:/register";
        }

        this.userService.register(userRegisterDTO);

        return "redirect:/login";
    }
}
