package com.example.battleships.web;

import com.example.battleships.model.dto.UserLoginDTO;
import com.example.battleships.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
public class UserLoginController {

    private UserService userService;


    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserLoginDTO initForm() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (userService.isLoggedIn()) {
            return "redirect:home";
        }

        model.addAttribute("activePage", "login");
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (userService.isLoggedIn()) {
            return "redirect:home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";


    }

    @GetMapping("/logout")
    public String logout(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("activePage", "index");
        userService.logout();
        return "redirect:/";
    }
}
