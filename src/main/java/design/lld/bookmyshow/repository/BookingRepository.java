package design.lld.bookmyshow.repository;


import design.lld.bookmyshow.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  // Standard CRUD methods
}

