package design.lld.bookmyshow.schedulers;

import design.lld.bookmyshow.Seat;
import design.lld.bookmyshow.enums.SeatStatus;
import design.lld.bookmyshow.repository.SeatRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class Scheduler {

  @Autowired
  private SeatRepository seatRepository;

  @Scheduled(fixedRateString = "60000") // Run every minute
  public void releaseExpiredReservations() {
    List<Seat> seatsToRelease = seatRepository.findReservedSeatsWithExpiredReservation(
        SeatStatus.RESERVED, LocalDateTime.now());
    for (Seat seat : seatsToRelease) {
      seat.setStatus(SeatStatus.AVAILABLE);
      seat.setReservationExpiryTime(null);
      seatRepository.save(seat);
    }
  }
}

