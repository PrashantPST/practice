package design.pattern.behavioral.chainofresponsibility.example1;


import java.net.http.HttpRequest;

public class AuthenticationHandler implements RequestHandler {
    private RequestHandler nextHandler;

    @Override
    public void setNextHandler(RequestHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(HttpRequest request) {
        // Authentication logic
        if (authenticated(request)) {
            System.out.println("Authentication successful.");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private boolean authenticated(HttpRequest request) {
        // Implement authentication logic
        return true;
    }
}
