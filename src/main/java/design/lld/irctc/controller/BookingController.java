package design.lld.irctc.controller;


import design.lld.irctc.Ticket;
import design.lld.irctc.dto.BookingRequest;
import design.lld.irctc.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookTicket")
    public ResponseEntity<Ticket> bookTicket(@RequestBody BookingRequest request) {
        return null;
    }
}

