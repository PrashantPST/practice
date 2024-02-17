package design.lld.bookmyshow.repository;

import design.lld.bookmyshow.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByName(String name);
    @Query("SELECT distinct m FROM Movie m " +
        "JOIN m.shows s " +
        "JOIN s.screen sc " +
        "JOIN sc.theater t " +
        "WHERE t.city.id = :cityId")
    List<Movie> findMoviesByCityId(@Param("cityId") Long cityId);
}