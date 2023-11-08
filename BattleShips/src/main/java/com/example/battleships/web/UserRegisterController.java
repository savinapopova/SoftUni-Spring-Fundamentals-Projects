package com.example.battleships.web;

import com.example.battleships.model.dto.UserRegistrationDTO;
import com.example.battleships.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    private UserService userService;


    @Autowired
    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String register(Model model) {

        if (userService.isLoggedIn()) {
            return "redirect:home";
        }
        model.addAttribute("activePage", "register");
        return "register";
    }

    @PostMapping
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (userService.isLoggedIn()) {
            return "redirect:home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",
                            bindingResult);

            return "redirect:/register";

        }

        this.userService.register(userRegistrationDTO);

        return "redirect:/login";
        }

    }

