package com.example.shoppinglist.web;

import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
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

        List<ProductDTO> foods = this.productService.getAllFoods();
        List<ProductDTO> drinks = this.productService.getAllDrinks();
        List<ProductDTO> household = this.productService.getAllHousehold();
        List<ProductDTO> others = this.productService.getAllOthers();

        model.addAttribute("foods", foods);
        model.addAttribute("drinks", drinks);
        model.addAttribute("household", household);
        model.addAttribute("others", others);


        BigDecimal totalSum = this.productService.getTotalSum();
        model.addAttribute("totalSum", totalSum);


        return "home";
    }

    @GetMapping("/product/add/{id}")
    public String buyProduct(@PathVariable Long id) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        Product product = this.productService.findProduct(id);

        this.productService.remove(product);

        return "redirect:/home";
    }

    @GetMapping("/product/buy/all")
    public String buyAll() {

       this.productService.deleteAll();

       return "redirect:/home";
    }
}
