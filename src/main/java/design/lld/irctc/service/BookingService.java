package design.lld.irctc.service;

import design.lld.irctc.SeatAvailability;
import design.lld.irctc.Ticket;
import design.lld.irctc.exception.SeatsNotAvailableException;
import design.lld.irctc.repository.SeatAvailabilityRepository;
import design.lld.irctc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BookingService {

    private final TicketRepository ticketRepository;
    private final SeatAvailabilityRepository seatAvailabilityRepository;

    @Autowired
    public BookingService(TicketRepository ticketRepository, SeatAvailabilityRepository seatAvailabilityRepository) {
        this.ticketRepository = ticketRepository;
        this.seatAvailabilityRepository = seatAvailabilityRepository;
    }

    @Transactional
    public Ticket bookTicket(Long userId, Long trainId, LocalDate travelDate, String classType, String berthPreference) {
        // Check seat availability
        SeatAvailability availability = seatAvailabilityRepository.findByTrainIdAndTravelDateAndClassTypeAndBerthType(
                trainId, travelDate, classType, berthPreference);

        if (availability != null && availability.getAvailableSeats() > 0) {
            // Create and save the ticket
            Ticket ticket = new Ticket();
            ticketRepository.save(ticket);

            // Update seat availability
            availability.setAvailableSeats(availability.getAvailableSeats() - 1);
            seatAvailabilityRepository.save(availability);

            return ticket;
        } else {
            throw new SeatsNotAvailableException("Seats are not available for the selected class and berth preference.");
        }
    }

    // Other service methods...
}
