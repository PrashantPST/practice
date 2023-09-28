package design.lld.elevator;

import design.lld.elevator.enums.Direction;
import design.lld.elevator.enums.ElevatorState;
import design.lld.elevator.models.Request;
import design.lld.elevator.service.Elevator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElevatorTest {

    private Elevator elevator;

    @Before
    public void setUp() {
        elevator = new Elevator();
    }

    @Test
    public void testBasicUpwardMovement() {
        // Add requests to the upQueue
        elevator.addRequest(new Request("1", Direction.UP, 3));
        elevator.addRequest(new Request("2", Direction.UP, 5));
        elevator.moveElevator();
        assertEquals(5, elevator.getCurrentFloor());

        // Verify that the elevator returns to the idle state
        assertEquals(ElevatorState.IDLE, elevator.getElevatorState());
    }

    @Test
    public void testBasicDownwardMovement() {
        // Add requests to the downQueue
        elevator.addRequest(new Request("1", Direction.DOWN, 8));
        elevator.addRequest(new Request("2", Direction.DOWN, 4));
        elevator.moveElevator();

        // Ensure the elevator stops at each requested floor
        assertEquals(4, elevator.getCurrentFloor());

        // Verify that the elevator returns to the idle state
        assertEquals(ElevatorState.IDLE, elevator.getElevatorState());
    }

    // Add more test methods for other scenarios as needed

}

