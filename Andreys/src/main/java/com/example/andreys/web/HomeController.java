package com.example.andreys.web;

import com.example.andreys.model.dto.ItemDTO;
import com.example.andreys.service.ProductService;
import com.example.andreys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;

    private UserService userService;

    @Autowired
    public HomeController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {

        if (this.userService.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        List<ItemDTO> items = this.productService.getItems();
        int count = items.size();

        model.addAttribute("items", items);
        model.addAttribute("count", count);

        return "home";
    }
}
