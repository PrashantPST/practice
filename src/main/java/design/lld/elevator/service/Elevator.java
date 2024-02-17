package design.lld.elevator.service;

import design.lld.elevator.enums.Direction;
import design.lld.elevator.enums.ElevatorState;
import design.lld.elevator.models.Request;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;

@Getter
public class Elevator {

  private final long id;
  private final PriorityBlockingQueue<Request> upQueue;
  private final PriorityBlockingQueue<Request> downQueue;
  private final Lock lock = new ReentrantLock();
  private int currentFloor;
  private ElevatorState elevatorState;
  private Direction direction;

  public Elevator(int id) {
    this.id = id;
    this.upQueue = new PriorityBlockingQueue<>(10,
        Comparator.comparingInt(Request::getDestinationFloor));
    this.downQueue = new PriorityBlockingQueue<>(10,
        (req1, req2) -> req2.getDestinationFloor() - req1.getDestinationFloor());
    this.currentFloor = 0;
    this.elevatorState = ElevatorState.IDLE;
    this.direction = Direction.UP;
  }

  public void moveElevator() {
    lock.lock();
    try {
      if (elevatorState == ElevatorState.IDLE) {
        prepareNextMovement();
      }

      if (direction == Direction.UP) {
        processQueue(upQueue);
      } else if (direction == Direction.DOWN) {
        processQueue(downQueue);
      }

      if (upQueue.isEmpty() && downQueue.isEmpty()) {
        elevatorState = ElevatorState.IDLE;
        System.out.println("Finished processing all requests");
      }
    } finally {
      lock.unlock();
    }
  }

  private void processQueue(PriorityBlockingQueue<Request> queue) {
    while (!queue.isEmpty()) {
      Request request = queue.poll();
      currentFloor = request.getDestinationFloor();
      System.out.println("Elevator " + this.id + " stopped at floor " + currentFloor);
    }
  }

  private void prepareNextMovement() {
    if (!upQueue.isEmpty()) {
      direction = Direction.UP;
      elevatorState = ElevatorState.MOVING;
    } else if (!downQueue.isEmpty()) {
      direction = Direction.DOWN;
      elevatorState = ElevatorState.MOVING;
    }
  }

  public void addRequest(Request request) {
    lock.lock();
    try {
      if (request.getDirection() == Direction.UP) {
        upQueue.offer(request);
      } else {
        downQueue.offer(request);
      }
    } finally {
      lock.unlock();
    }
  }
}
