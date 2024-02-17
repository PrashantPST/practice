package design.pattern.behavioral.chainofresponsibility;

public class ConcreteHandlerB extends Handler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("B")) {
            System.out.println("Handler B handled the request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
