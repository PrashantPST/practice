package design.lld.snakeandladder.exceptions;

public class GameAlreadyStartedException extends RuntimeException {

    public GameAlreadyStartedException(String message) {
        super(message);
    }
}
