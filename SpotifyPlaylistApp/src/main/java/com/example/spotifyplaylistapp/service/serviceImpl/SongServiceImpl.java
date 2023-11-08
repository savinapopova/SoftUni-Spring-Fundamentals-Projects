package com.example.spotifyplaylistapp.service.serviceImpl;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;
    private StyleRepository styleRepository;

    private UserRepository userRepository;

    private UserService userService;


    @Autowired
    public SongServiceImpl(SongRepository songRepository, StyleRepository styleRepository, UserRepository userRepository, UserService userService) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void register(CreateSongDTO createSongDTO) {

        Style style = this.styleRepository.findByName(StyleName.valueOf(createSongDTO.getStyle()));

        Song song = new Song(createSongDTO.getPerformer(),
                             createSongDTO.getTitle(),
                createSongDTO.getDuration(),
                createSongDTO.getReleaseDate(),
                style
                );
        this.songRepository.save(song);


    }

    @Override
    public List<SongDTO> getAllPopSongs() {
        Style pop = this.styleRepository.findByName(StyleName.POP);
        List<Song> popSongs = this.songRepository.findAllByStyle(pop);
        return popSongs.stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongDTO> getAllRockSongs() {
        Style rock = this.styleRepository.findByName(StyleName.ROCK);
        List<Song> rockSongs = this.songRepository.findAllByStyle(rock);
        return rockSongs.stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SongDTO> getAllJazzSongs() {
        Style jazz = this.styleRepository.findByName(StyleName.JAZZ);
        List<Song> jazzSongs = this.songRepository.findAllByStyle(jazz);
        return jazzSongs.stream()
                .map(SongDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addToPlaylist(Long id) {
        Optional<Song> optionalSong = this.songRepository.findById(id);
        User user = this.userService.getLoggedUser();
        Song song = optionalSong.get();
        user.getPlaylist().add(song);
        this.userRepository.save(user);
    }

    @Override
    public List<SongDTO> getPlaylist() {
        User user = this.userService.getLoggedUser();
        return user.getPlaylist()
                .stream().map(SongDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPlaylistDuration() {
        int totalSeconds = this.userService.getLoggedUser()
                .getPlaylist().stream()
                .mapToInt(Song::getDuration)
                .sum();
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        StringBuilder builder = new StringBuilder();
        builder.append(min)
                .append(":");
        if (sec < 10) {
            builder.append(0);
        }
        builder.append(sec);
        return builder.toString();
    }
}
