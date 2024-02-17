package design.lld.elevator.strategy.impl;

import design.lld.elevator.enums.ElevatorState;
import design.lld.elevator.models.Request;
import design.lld.elevator.service.Elevator;

import design.lld.elevator.strategy.ElevatorSelectionStrategy;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {
        Elevator nearestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getDestinationFloor());
            if (distance < minDistance && elevator.getElevatorState() == ElevatorState.IDLE) {
                nearestElevator = elevator;
                minDistance = distance;
            }
        }

        return nearestElevator != null ? nearestElevator : elevators.get(0);
    }
}

