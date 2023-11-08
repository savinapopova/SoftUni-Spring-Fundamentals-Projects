package com.example.musicdb.web;

import com.example.musicdb.model.dto.AlbumDTO;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AlbumService albumService;
    private UserService userService;

    @Autowired
    public HomeController(AlbumService albumService, UserService userService) {
        this.albumService = albumService;
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

        List<AlbumDTO> albums = this.albumService.getAlbums();

        model.addAttribute("albums", albums);

        int totalCopies = albums.stream().mapToInt(AlbumDTO::getCopies).sum();

        model.addAttribute("totalCopies", totalCopies);


        return "home";
    }
}
