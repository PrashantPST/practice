package design.lld.elevator.models;

import design.lld.elevator.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Request {

    private String id;
    Direction direction;
    private int destinationFloor;
}
