package design.lld.elevator;

import design.lld.elevator.enums.Direction;
import design.lld.elevator.models.Request;
import design.lld.elevator.service.ElevatorManager;
import design.lld.elevator.strategy.impl.LeastBusyElevatorStrategy;
import design.lld.elevator.strategy.ElevatorSelectionStrategy;

public class MultipleElevatorSystem {
    public static void main(String[] args) {
        ElevatorSelectionStrategy strategy = new LeastBusyElevatorStrategy();
        ElevatorManager manager = new ElevatorManager(3, strategy);

        // Add requests
        manager.addRequest(new Request("1", Direction.UP, 5));
        manager.addRequest(new Request("2", Direction.DOWN, 3));

        // Move elevators concurrently
        manager.moveElevators();

        // Shut down the executor service when done
        manager.shutdown();
    }
}
