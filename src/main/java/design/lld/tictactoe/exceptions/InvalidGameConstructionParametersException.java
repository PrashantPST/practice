package design.lld.tictactoe.exceptions;

public class InvalidGameConstructionParametersException extends RuntimeException {
    public InvalidGameConstructionParametersException(String message) {
        super(message);
    }
}
