package design.lld.irctc;

import design.lld.irctc.enums.SeatClass;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "SeatAvailability")
@Data
public class SeatAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long availabilityId;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    @Column(name = "class_type")
    private SeatClass classType;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "total_seats")
    private int totalSeats;

    // Constructors, Getters, Setters
}

