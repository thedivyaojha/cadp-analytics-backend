package com.cadp.portal.analytics.repository;

import com.cadp.portal.analytics.domain.song.Song;
import com.cadp.portal.analytics.domain.song.SongStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SongRepository extends JpaRepository<Song, String> {
    List<Song> findByStatus(SongStatus status);
    Optional<Song> findByIsrc(String isrc);

    List<Song> findByArtistName(String artistName);
}
