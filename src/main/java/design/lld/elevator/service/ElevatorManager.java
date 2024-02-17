package design.lld.elevator.service;

import design.lld.elevator.models.Request;
import design.lld.elevator.strategy.ElevatorSelectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorManager {

  private final List<Elevator> elevators;
  private final ElevatorSelectionStrategy selectionStrategy;
  private final ExecutorService executorService;

  public ElevatorManager(int numberOfElevators, ElevatorSelectionStrategy strategy) {
    this.elevators = new ArrayList<>();
    this.selectionStrategy = strategy;
    this.executorService = Executors.newFixedThreadPool(numberOfElevators);
    for (int i = 1; i <= numberOfElevators; i++) {
      elevators.add(new Elevator(i));
    }
  }

  public void addRequest(Request request) {
    Elevator selectedElevator = selectionStrategy.selectElevator(elevators, request);
    selectedElevator.addRequest(request);
  }

  public void moveElevators() {
    for (Elevator elevator : elevators) {
      executorService.submit(elevator::moveElevator);
    }
  }

  public void shutdown() {
    executorService.shutdown();
  }

}

