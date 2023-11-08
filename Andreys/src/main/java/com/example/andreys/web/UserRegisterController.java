package com.example.andreys.web;

import com.example.andreys.model.dto.UserRegisterDTO;
import com.example.andreys.service.UserService;
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
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/users/register")
    public String register() {

        if (this.userService.isLogged()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (this.userService.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
                            bindingResult);
            return "redirect:/users/register";
        }
        if (!this.userService.passwordsMatch(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes
                    .addFlashAttribute("passwordsMatch", false);
            return "redirect:/users/register";
        }
        if (!this.userService.checkAvailable(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes
                    .addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }
        this.userService.register(userRegisterDTO);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }
        this.userService.logout();
        return "redirect:/";
    }
}
