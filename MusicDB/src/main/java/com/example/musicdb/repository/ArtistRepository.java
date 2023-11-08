package com.example.musicdb.repository;

import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.enums.SingerName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(SingerName name);
}
