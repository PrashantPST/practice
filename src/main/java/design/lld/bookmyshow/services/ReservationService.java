package design.lld.bookmyshow.services;

import design.lld.bookmyshow.Reservation;
import design.lld.bookmyshow.Seat;
import design.lld.bookmyshow.dtos.PaymentResult;
import design.lld.bookmyshow.enums.SeatStatus;
import design.lld.bookmyshow.exceptions.PaymentFailedException;
import design.lld.bookmyshow.repository.ReservationRepository;
import design.lld.bookmyshow.repository.SeatRepository;
import design.lld.splitwise.models.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  private static final long RESERVATION_EXPIRY_DURATION_MINUTES = 10;
  @Autowired
  private SeatRepository seatRepository;
  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private PaymentService paymentService;

  @Transactional
  public boolean reserveSeats(List<Long> seatIds) {
    LocalDateTime now = LocalDateTime.now();
    List<Seat> seatsToReserve = new ArrayList<>();

    for (Long seatId : seatIds) {
      Seat seat = seatRepository.findByIdForUpdate(seatId)
          .orElseThrow(() -> new IllegalStateException("Seat not found: " + seatId));

      if (seat.getStatus() != SeatStatus.AVAILABLE) {
        throw new IllegalStateException("Seat is already booked or not available: " + seatId);
      }

      seat.setStatus(SeatStatus.RESERVED);
      seat.setReservationExpiryTime(now.plusMinutes(RESERVATION_EXPIRY_DURATION_MINUTES));
      seatsToReserve.add(seat);
    }
    seatRepository.saveAll(seatsToReserve); // Batch update
    return true;
  }

  private BigDecimal calculateTotalPrice(List<Seat> seats) {
    return seats.stream()
        .map(Seat::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Transactional
  public Reservation createBooking(List<Seat> seats, User user) {
    BigDecimal totalPrice = calculateTotalPrice(seats);
    PaymentResult paymentResult = paymentService.processPayment(user, totalPrice);

    if (paymentResult.isSuccessful()) {
      reserveAndBookSeats(seats);
      return createAndSaveReservation(seats);
    } else {
      throw new PaymentFailedException(paymentResult.getErrorMessage());
    }
  }

  private void reserveAndBookSeats(List<Seat> seats) {
    List<Long> seatIds = seats.stream().map(Seat::getId).collect(Collectors.toList());
    if (!reserveSeats(seatIds)) {
      throw new IllegalStateException("Seat reservation failed.");
    }
    seats.forEach(seat -> {
      seat.setStatus(SeatStatus.BOOKED);
      seat.setReservationExpiryTime(null);
    });
    seatRepository.saveAll(seats); // Batch update
  }

  private Reservation createAndSaveReservation(List<Seat> seats) {
    Reservation reservation = new Reservation();
    reservation.setSeats(seats);
    reservation.setBookingTime(LocalDateTime.now());
    // Add other necessary booking details here
    return reservationRepository.save(reservation);
  }
}
