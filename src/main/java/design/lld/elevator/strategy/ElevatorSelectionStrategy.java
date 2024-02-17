package design.lld.elevator.strategy;

import design.lld.elevator.models.Request;
import design.lld.elevator.service.Elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}

