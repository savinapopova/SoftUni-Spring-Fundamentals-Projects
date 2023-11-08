package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private SongService songService;

   @Autowired
    public HomeController(UserService userService, SongService songService) {
        this.userService = userService;
       this.songService = songService;
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

       List<SongDTO> popSongs = this.songService.getAllPopSongs();
       List<SongDTO> rockSongs = this.songService.getAllRockSongs();
       List<SongDTO> jazzSongs = this.songService.getAllJazzSongs();

       model.addAttribute("popSongs", popSongs);
       model.addAttribute("rockSongs", rockSongs);
       model.addAttribute("jazzSongs", jazzSongs);

       List<SongDTO> playlist = this.songService.getPlaylist();
       String totalDuration = this.songService.getPlaylistDuration();

       model.addAttribute("playlist", playlist);
       model.addAttribute("totalDuration", totalDuration);




        return "home";
    }

    @GetMapping("/playlist/add/{id}")
    public String addToPlaylist(@PathVariable Long id) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

       this.songService.addToPlaylist(id);

       return "redirect:/home";
    }

    @GetMapping("/playlist/remove")
    public String clearPlaylist() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        this.userService.clearPlaylist();

        return "redirect:/home";
    }
}
