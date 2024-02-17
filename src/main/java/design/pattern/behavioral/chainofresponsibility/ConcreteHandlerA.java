package design.pattern.behavioral.chainofresponsibility;

public class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("A")) {
            System.out.println("Handler A handled the request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
