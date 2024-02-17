package design.lld.irctc;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "TrainSchedule")
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @Column(name = "departure_time")
    private Time departureTime;
    private Integer distanceFromStart;
    @Column(name = "stop_number")
    private int stopNumber;
}

