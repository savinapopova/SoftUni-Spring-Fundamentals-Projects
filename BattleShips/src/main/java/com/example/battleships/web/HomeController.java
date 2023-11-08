package com.example.battleships.web;

import com.example.battleships.model.dto.ShipDTO;
import com.example.battleships.model.dto.StartBattleDTO;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;

    private ShipService shipService;

    @Autowired
    public HomeController(UserService userService, ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }

    @ModelAttribute
    public StartBattleDTO init() {
        return new StartBattleDTO();
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!userService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("activePage", "home");

        long loggedUserId = userService.getLoggedId();

        List<ShipDTO> ownShips = shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);


        return "home";
    }

    @GetMapping("/")
    public String loggedOutIndex(Model model) {

        if (userService.isLoggedIn()) {
            return "redirect:/home";
        }
        model.addAttribute("activePage", "index");
        return "index";
    }
}
