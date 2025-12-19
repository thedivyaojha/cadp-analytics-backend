package com.cadp.portal.analytics.domain.song;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(
        name = "songs",
        indexes = {
                @Index(name = "idx_songs_artist", columnList = "artist_name"),
                @Index(name = "idx_songs_status", columnList = "status")
        }
)
public class Song {

    @Id
    @Column(length = 20)
    private String isrc;

    @Column(name = "song_name", nullable = false, length = 255)
    private String songName;

    @Column(name = "artist_name", nullable = false, length = 255)
    private String artistName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SongStatus status;

    /**
     * Flexible platform URLs:
     * {
     *   "spotify": "https://...",
     *   "apple": "https://...",
     *   "youtube": "https://..."
     * }
     */
    @Column(
            name = "platform_links",
            columnDefinition = "jsonb"
    )
    @Convert(converter = PlatformLinksConverter.class)
    private Map<String, String> platformLinks;

    // ----- Constructors -----

    protected Song() {
        // for JPA
    }

    public Song(
            String isrc,
            String songName,
            String artistName,
            SongStatus status,
            Map<String, String> platformLinks
    ) {
        this.isrc = isrc;
        this.songName = songName;
        this.artistName = artistName;
        this.status = status;
        this.platformLinks = platformLinks;
    }

    // ----- Getters -----

    public String getIsrc() {
        return isrc;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public SongStatus getStatus() {
        return status;
    }

    public Map<String, String> getPlatformLinks() {
        return platformLinks;
    }

    // ----- Domain behavior (important later) -----

    public void updateStatus(SongStatus newStatus) {
        this.status = newStatus;
    }
}
