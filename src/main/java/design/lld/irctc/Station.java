package design.lld.irctc;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @Column(nullable = false, unique = true)
    private String code; // Unique station code

    @Column(nullable = false)
    private String name; // Station name

    private String city; // City the station is located in

    // Bidirectional relationship with Train
    // Assuming a train has source and destination stations
    @OneToMany(mappedBy = "sourceStation", cascade = CascadeType.ALL)
    private Set<Train> trainsDeparting;

    @OneToMany(mappedBy = "destinationStation", cascade = CascadeType.ALL)
    private Set<Train> trainsArriving;

    // Constructors, Getters, and Setters

    public Station() {
    }

    public Station(String code, String name, String city) {
        this.code = code;
        this.name = name;
        this.city = city;
    }

    // Additional getters and setters
}
