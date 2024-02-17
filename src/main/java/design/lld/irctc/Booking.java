package design.lld.irctc;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private double totalFare;

    private String paymentStatus; // e.g., Paid, Pending, Cancelled

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    // Constructors, Getters, and Setters

    public Booking() {
    }

    public Booking(double totalFare, String paymentStatus, Date bookingDate) {
        this.totalFare = totalFare;
        this.paymentStatus = paymentStatus;
        this.bookingDate = bookingDate;
    }

    // Get number of passengers
    public int getNumberOfPassengers() {
        return tickets != null ? tickets.size() : 0;
    }
}


