package design.lld.elevator.strategy.impl;

import design.lld.elevator.models.Request;
import design.lld.elevator.service.Elevator;
import design.lld.elevator.strategy.ElevatorSelectionStrategy;

import java.util.Comparator;
import java.util.List;

public class LeastBusyElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        return elevators.stream()
                .min(Comparator.comparingInt(elevator -> elevator.getUpQueue().size() +
                        elevator.getDownQueue().size()))
                .orElse(elevators.get(0));
    }
}

