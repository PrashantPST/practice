package design.lld.irctc.service;

import design.lld.irctc.SearchFilter;
import design.lld.irctc.Station;
import design.lld.irctc.Train;
import design.lld.irctc.repository.RouteRepository;
import design.lld.irctc.repository.TrainRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainSearchService {
    private TrainRepository trainRepository;
    private RouteRepository routeRepository;

    public List<Train> searchTrains(Station source, Station destination, LocalDate date, SearchFilter filter) {
        return new ArrayList<>();
    }
}
