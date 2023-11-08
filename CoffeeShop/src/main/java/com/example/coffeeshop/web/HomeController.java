package com.example.coffeeshop.web;

import com.example.coffeeshop.model.dto.OrderDTO;
import com.example.coffeeshop.model.dto.UserDTO;
import com.example.coffeeshop.model.enums.CategoryName;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public HomeController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
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

        List<OrderDTO> coffeeList = this.orderService.getOrderList(CategoryName.COFFEE);
        List<OrderDTO> cakeList = this.orderService.getOrderList(CategoryName.CAKE);
        List<OrderDTO> drinkList = this.orderService.getOrderList(CategoryName.DRINK);
        List<OrderDTO> otherList = this.orderService.getOrderList(CategoryName.OTHER);

        model.addAttribute("coffeeList", coffeeList);
        model.addAttribute("cakeList", cakeList);
        model.addAttribute("drinkList", drinkList);
        model.addAttribute("otherList", otherList);

        int totalTime = drinkList.size() + coffeeList.size() * 2
                + otherList.size() * 5 + cakeList.size() * 10;

        model.addAttribute("totalTime", totalTime);


        List<UserDTO> users = this.userService.getAllUsers();

        model.addAttribute("users", users);

           return "home";
    }

    @GetMapping("order/ready/{id}")
    public String orderReady(@PathVariable Long id) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        this.orderService.remove(id);

        return "redirect:/home";
    }
}
