package com.example.shoppinglist.web;

import com.example.shoppinglist.model.dto.CreateProductDTO;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/product/add")
    public String addProduct() {
        if (!this.userService.isLogged()) {
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/product/add")
    public String addProduct(@Valid CreateProductDTO createProductDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createProductDTO", createProductDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createProductDTO",
                            bindingResult);
            return "redirect:/product/add";
        }
            if(this.productService.productExists(createProductDTO)) {
                redirectAttributes.addFlashAttribute("createProductDTO", createProductDTO);
                redirectAttributes
                        .addFlashAttribute("alreadyExists", true);
                return "redirect:/product/add";
            }

            this.productService.register(createProductDTO);

                return "redirect:/home";
    }
}
