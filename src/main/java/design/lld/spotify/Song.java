package design.lld.spotify;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "albumId")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genreId")
    private Genre genre;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private Integer duration; // Duration in seconds

    // Constructors, getters and setters
}

