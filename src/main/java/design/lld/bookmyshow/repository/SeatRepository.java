package design.lld.bookmyshow.repository;

import design.lld.bookmyshow.Seat;
import design.lld.bookmyshow.enums.SeatStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

  @Query("SELECT s FROM Seat s WHERE s.screen.screenId IN " +
      "(SELECT st.screen.screenId FROM Showtime st WHERE st.movie.movieId = :movieId) " +
      "AND s.isBooked = false")
  List<Seat> findAvailableSeatsByMovie(@Param("movieId") Long movieId);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT s FROM Seat s WHERE s.id = :id")
  Optional<Seat> findByIdForUpdate(@Param("id") Long id);

  @Query("SELECT s FROM Seat s WHERE s.status = :status AND s.reservationExpiryTime < :expiryTime")
  List<Seat> findReservedSeatsWithExpiredReservation(@Param("status") SeatStatus status,
      @Param("expiryTime") LocalDateTime expiryTime);

  void saveAll(Iterable<Seat> seats);

}

