package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.TicketStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ParkingTicket {
    private String ticketNumber;
    private String licensePlateNumber;
    private String allocatedSpotId;
    private LocalDateTime issuedAt;
    private LocalDateTime vacatedAt;
    private double charge;
    private TicketStatus ticketStatus;
}
