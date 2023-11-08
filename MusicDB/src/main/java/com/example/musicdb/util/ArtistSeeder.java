package com.example.musicdb.util;

import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.enums.SingerName;
import com.example.musicdb.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistSeeder implements CommandLineRunner {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistSeeder(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.artistRepository.count() == 0) {
            List<Artist> artists = Arrays.stream(SingerName.values())
                    .map(Artist::new)
                    .collect(Collectors.toList());
            this.artistRepository.saveAll(artists);
        }

    }
}
