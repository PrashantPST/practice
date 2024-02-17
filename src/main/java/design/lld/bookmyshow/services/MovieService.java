package design.lld.bookmyshow.services;

import design.lld.bookmyshow.Movie;
import design.lld.bookmyshow.Seat;
import design.lld.bookmyshow.Show;
import design.lld.bookmyshow.repository.MovieRepository;
import design.lld.bookmyshow.repository.SeatRepository;
import design.lld.bookmyshow.repository.ShowRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  private SeatRepository seatRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private ShowRepository showRepository;

  @Autowired

  public List<Seat> checkSeatAvailability(Long movieId) {
    return seatRepository.findAvailableSeatsByMovie(movieId);
  }

  public List<Show> searchMovieShowtimesByName(String movieName) {
    return movieRepository.findByName(movieName)
        .map(movie -> showRepository.findByMovieId(movie.getMovieId()))
        .orElse(Collections.emptyList());
  }

  public List<Movie> getMovies(Long cityId) {
    return movieRepository.findMoviesByCityId(cityId);
  }
}

