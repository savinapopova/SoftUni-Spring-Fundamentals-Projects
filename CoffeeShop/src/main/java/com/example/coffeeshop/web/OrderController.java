package com.example.coffeeshop.web;

import com.example.coffeeshop.model.dto.CreateOrderDTO;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateOrderDTO init() {
        return new CreateOrderDTO();
    }

    @GetMapping("/order/add")
    public String addOrder() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        return "order-add";
    }

    @PostMapping("/order/add")
    public String addOrder(@Valid CreateOrderDTO createOrderDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createOrderDTO", createOrderDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createOrderDTO",
                            bindingResult);
            return "redirect:/order/add";
        }

        this.orderService.register(createOrderDTO);

        return "redirect:/home";
    }
}
