package design.lld.imdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 1024)
    private String biography;
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    // Constructors, getters and setters
}

