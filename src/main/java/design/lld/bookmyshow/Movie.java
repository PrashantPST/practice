package design.lld.bookmyshow;

import design.lld.bookmyshow.enums.Genre;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "movies")
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String name;
    private String director;
    private String cast;
    private Integer duration;
    private LocalDate releaseDate;
    private Genre genre;
    private String language;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Show> shows;
}
