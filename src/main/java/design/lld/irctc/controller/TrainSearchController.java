package design.lld.irctc.controller;

import design.lld.irctc.Station;
import design.lld.irctc.Train;
import design.lld.irctc.service.TrainSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainSearchController {

    private final TrainSearchService trainSearchService;

    @Autowired
    public TrainSearchController(TrainSearchService trainSearchService) {
        this.trainSearchService = trainSearchService;
    }

    @GetMapping("/searchTrains")
    public ResponseEntity<List<Train>> searchTrains(
            @RequestParam Station sourceStation,
            @RequestParam Station destinationStation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Train> trains = trainSearchService.searchTrains(sourceStation, destinationStation, null, null);
        return ResponseEntity.ok(trains);
    }
}

