package design.lld.bookmyshow;

import design.lld.bookmyshow.enums.BookingStatus;
import design.lld.splitwise.models.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookingId;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @ManyToOne
  @JoinColumn(name = "show_id", nullable = false)
  private Show show;
  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Seat> seats;
  private LocalDateTime bookingTime;
  private BigDecimal totalPrice;
  private BookingStatus status;
  private Payment payment;
}

