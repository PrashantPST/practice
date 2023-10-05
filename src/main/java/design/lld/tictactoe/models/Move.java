package design.lld.tictactoe.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Move {

    private Player player;
    private Cell cell;
}
