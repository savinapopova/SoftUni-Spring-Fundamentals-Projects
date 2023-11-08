package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;

import java.util.List;

public interface SongService {
    void register(CreateSongDTO createSongDTO);

    List<SongDTO> getAllPopSongs();

    List<SongDTO> getAllRockSongs();

    List<SongDTO> getAllJazzSongs();

    void addToPlaylist(Long id);

    List<SongDTO> getPlaylist();

    String getPlaylistDuration();
}
