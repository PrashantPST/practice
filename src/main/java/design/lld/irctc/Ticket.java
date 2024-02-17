package design.lld.irctc;

import design.lld.parkinglot.enums.TicketStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private String classOfTravel; // e.g., Sleeper, AC 3-tier, etc.

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    // Constructors, Getters, and Setters

    public Ticket() {
    }

    public Ticket(Passenger passenger, Train train, String classOfTravel, Date bookingDate, TicketStatus status) {
        this.passenger = passenger;
        this.train = train;
        this.classOfTravel = classOfTravel;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Additional getters and setters
}

