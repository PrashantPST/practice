package design.lld.urlshortener.exceptions;

public class CustomAliasAlreadyExistsException extends RuntimeException {
    public CustomAliasAlreadyExistsException(String message) {
        super(message);
    }
}
