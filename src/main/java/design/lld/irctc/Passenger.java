package design.lld.irctc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    @Column(nullable = false)
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String contactInfo;

    // Assuming a relationship with a Booking entity
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // Constructors, Getters, and Setters

    public Passenger() {
    }

    public Passenger(String name, int age, Gender gender, String contactInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    // Additional getters and setters
}

