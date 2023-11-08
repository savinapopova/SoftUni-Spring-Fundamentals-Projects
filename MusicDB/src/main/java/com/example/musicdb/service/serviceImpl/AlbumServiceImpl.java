package com.example.musicdb.service.serviceImpl;

import com.example.musicdb.model.dto.AlbumDTO;
import com.example.musicdb.model.dto.CreateAlbumDTO;
import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.enums.Genre;
import com.example.musicdb.model.enums.SingerName;
import com.example.musicdb.repository.AlbumRepository;
import com.example.musicdb.repository.ArtistRepository;
import com.example.musicdb.repository.UserRepository;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    private ArtistRepository artistRepository;

    private LoggedUser userSession;

    private UserRepository userRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistRepository artistRepository, LoggedUser userSession, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }


    @Override
    public void register(CreateAlbumDTO createAlbumDTO) {

        Artist artist = this.artistRepository
                .findByName(SingerName.valueOf(createAlbumDTO.getArtist()));
        Optional<User> user = this.userRepository.findById(this.userSession.getId());


        Album album = new Album(createAlbumDTO.getName(),
                createAlbumDTO.getImageUrl(),
                createAlbumDTO.getDescription(),
                createAlbumDTO.getCopies(),
                createAlbumDTO.getPrice(),
                createAlbumDTO.getReleaseDate(),
                createAlbumDTO.getProducer(),
                Genre.valueOf(createAlbumDTO.getGenre()),
                artist,
                user.get()
                );
        this.albumRepository.save(album);
    }

    @Override
    public List<AlbumDTO> getAlbums() {
        List<Album> albums = this.albumRepository.findAll();
        return albums.stream().map(AlbumDTO::new).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        this.albumRepository.deleteById(id);
    }
}
