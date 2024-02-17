package design.lld.bookmyshow.repository;

import design.lld.bookmyshow.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  // Standard CRUD methods
}

