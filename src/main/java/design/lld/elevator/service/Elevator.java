package design.lld.elevator.service;

import design.lld.elevator.enums.Direction;
import design.lld.elevator.enums.ElevatorState;
import design.lld.elevator.models.Request;
import lombok.Getter;

import java.util.Comparator;
import java.util.PriorityQueue;


@Getter
public class Elevator {

    private final PriorityQueue<Request> upQueue;
    private final PriorityQueue<Request> downQueue;
    private int currentFloor;
    private ElevatorState elevatorState;
    private Direction direction;

    public Elevator() {
        this.upQueue = new PriorityQueue<>(Comparator.comparingInt(Request::getDestinationFloor));
        this.downQueue = new PriorityQueue<>((req1, req2) -> req2.getDestinationFloor() - req1.getDestinationFloor());
        this.elevatorState = ElevatorState.IDLE;
    }

    public void moveElevator() { //scheduler will call this method on an intermittent basis
        if (this.elevatorState == ElevatorState.IDLE && (!upQueue.isEmpty() || !downQueue.isEmpty())) {
            processRequest();
        }
        System.out.println("Finished processing all requests");
        this.elevatorState = ElevatorState.IDLE;
    }

    private void processRequest() {
        if (this.direction == Direction.UP || this.elevatorState == ElevatorState.IDLE) {
            processUpRequest();
            processDownRequest();
        } else if (this.direction == Direction.DOWN) {
            processDownRequest();
            processUpRequest();
        }
    }

    private void processDownRequest() {
        while (!downQueue.isEmpty()) {
            Request request = downQueue.poll();
            // do some hardware operation (go to that floor and open the door)
            this.currentFloor = request.getDestinationFloor();
            System.out.println("Elevator stopped at floor no " + this.currentFloor);
        }
        if (upQueue.isEmpty()) {
            this.elevatorState = ElevatorState.IDLE;
        } else {
            this.direction = Direction.UP;
        }
    }

    private void processUpRequest() {
        while (!upQueue.isEmpty()) {
            Request request = upQueue.poll();
            // do some hardware operation (go to that floor and open the door)
            this.currentFloor = request.getDestinationFloor();
            System.out.println("Elevator stopped at floor no " + this.currentFloor);
        }
        if (downQueue.isEmpty()) {
            this.elevatorState = ElevatorState.IDLE;
        } else {
            this.direction = Direction.DOWN;
        }
    }

    public void addRequest(Request request) {
        if (request.getDirection().equals(Direction.UP)) {
            upQueue.offer(request);
        }
        else {
            downQueue.offer(request);
        }
    }
}
