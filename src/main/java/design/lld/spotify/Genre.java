package design.lld.spotify;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Song> songs;

    // Constructors, getters and setters
}

