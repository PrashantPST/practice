package design.lld.bookmyshow;

import design.lld.bookmyshow.enums.SeatStatus;
import design.lld.bookmyshow.enums.SeatType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "show_id", nullable = false)
  private Show show;
  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;
  private String number;
  private BigDecimal price;
  private SeatType type;
  private SeatStatus status;
  private LocalDateTime bookingExpiryTime;
}

