package com.example.musicdb.web;

import com.example.musicdb.model.dto.CreateAlbumDTO;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlbumController {

    private AlbumService albumService;
    private UserService userService;

    @Autowired
    public AlbumController(AlbumService albumService, UserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateAlbumDTO init() {
        return new CreateAlbumDTO();
    }

    @GetMapping("/album/add")
    public String addAlbum() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        return "add-album";
    }

    @PostMapping("/album/add")
    public String addAlbum(@Valid CreateAlbumDTO createAlbumDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createAlbumDTO", createAlbumDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createAlbumDTO",
                            bindingResult);
            return "redirect:/album/add";
}
        this.albumService.register(createAlbumDTO);


        return "redirect:/home";
    }

    @GetMapping("/album/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        this.albumService.delete(id);
        return "redirect:/home";
    }
}
