package com.example.musicdb.service;

import com.example.musicdb.model.dto.AlbumDTO;
import com.example.musicdb.model.dto.CreateAlbumDTO;

import java.util.List;

public interface AlbumService {
    void register(CreateAlbumDTO createAlbumDTO);

    List<AlbumDTO> getAlbums();

    void delete(Long id);
}
