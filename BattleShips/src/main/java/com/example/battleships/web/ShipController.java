package com.example.battleships.web;

import com.example.battleships.model.dto.CreateShipDTO;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private ShipService shipService;
    private UserService userService;

    @Autowired
    public ShipController(ShipService shipService, UserService userService) {
        this.shipService = shipService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateShipDTO init() {
        return new CreateShipDTO();
    }

    @GetMapping("ships/add")
    public String addShip(Model model) {
        if (!userService.isLoggedIn()) {
           return  "redirect:/";
        }

        model.addAttribute("activePage", "add-ship");
        return "ship-add";
    }

    @PostMapping("ships/add")
    public String addShip(@Valid CreateShipDTO createShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!userService.isLoggedIn()) {
            return  "redirect:/";
        }

        if (bindingResult.hasErrors() || !shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO",
                    bindingResult);

            return "redirect:/ships/add";

        }

        return "redirect:/home";
    }


}
