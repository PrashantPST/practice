package design.lld.bookmyshow.controllers;

import design.lld.bookmyshow.Seat;
import design.lld.bookmyshow.Show;
import design.lld.bookmyshow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{movieId}/available-seats")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long movieId) {
        List<Seat> availableSeats = movieService.checkSeatAvailability(movieId);
        return ResponseEntity.ok(availableSeats);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Show>> getMovieShows(@RequestParam String name) {
        List<Show> shows = movieService.searchMovieShowtimesByName(name);
        if (shows.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shows);
    }
}

