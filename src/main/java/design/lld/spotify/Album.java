package design.lld.spotify;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @ManyToMany
    @JoinTable(
            name = "album_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;

    // Constructors, getters and setters
}

