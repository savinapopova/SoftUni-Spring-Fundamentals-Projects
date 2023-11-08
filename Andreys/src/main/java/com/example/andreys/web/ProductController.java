package com.example.andreys.web;

import com.example.andreys.model.dto.CreateProductDTO;
import com.example.andreys.model.dto.ItemDTO;
import com.example.andreys.service.ProductService;
import com.example.andreys.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateProductDTO init() {
        return new CreateProductDTO();
    }

    @GetMapping("/products/add")
    public String addProduct() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        return "add-product";
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid CreateProductDTO createProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("createProductDTO", createProductDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createProductDTO",
                            bindingResult);
            return "redirect:/products/add";
        }
        if (!this.productService.checkAvailable(createProductDTO)) {
            redirectAttributes
                    .addFlashAttribute("createProductDTO", createProductDTO);
            redirectAttributes
                    .addFlashAttribute("productExists",true);
            return "redirect:/products/add";
        }
        this.productService.register(createProductDTO);
        return "redirect:/home";
    }

    @GetMapping("/products/details/{name}")
    public String showItemDetails(@PathVariable String name,
                                  Model model) {
        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        ItemDTO item = this.productService.getProduct(name);

        model.addAttribute("item", item);

        return "details-product";
    }

   @Transactional
    @GetMapping("/products/delete/{name}")
    public String deleteItem(@PathVariable String name) {

       if (!this.userService.isLogged()) {
           return "redirect:/";
       }

        this.productService.removeProduct(name);
        return "redirect:/home";
    }
}
