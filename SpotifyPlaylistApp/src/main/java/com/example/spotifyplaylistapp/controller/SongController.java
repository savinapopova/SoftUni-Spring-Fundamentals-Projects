package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {

    private SongService songService;
    private UserService userService;

    @Autowired
    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateSongDTO init() {
        return new CreateSongDTO();
    }

    @GetMapping("/songs/add")
    public String addSong() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/songs/add")
    public String addSong(@Valid CreateSongDTO createSongDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("createSongDTO", createSongDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createSongDTO",
                            bindingResult);

            return "redirect:/songs/add";
        }
        this.songService.register(createSongDTO);

        return "redirect:/home";
    }
}
