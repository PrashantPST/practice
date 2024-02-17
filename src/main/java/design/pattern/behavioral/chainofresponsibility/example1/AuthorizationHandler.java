package design.pattern.behavioral.chainofresponsibility.example1;


import java.net.http.HttpRequest;

public class AuthorizationHandler implements RequestHandler {
    private RequestHandler nextHandler;

    @Override
    public void setNextHandler(RequestHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(HttpRequest request) {
        // Authorization logic
        if (authorized(request)) {
            System.out.println("Authorization successful.");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        } else {
            System.out.println("Authorization failed.");
        }
    }

    private boolean authorized(HttpRequest request) {
        // Implement authorization logic
        return false;
    }
}
