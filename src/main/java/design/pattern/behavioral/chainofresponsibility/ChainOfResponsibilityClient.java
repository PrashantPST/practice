package design.pattern.behavioral.chainofresponsibility;

public class ChainOfResponsibilityClient {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        handlerA.setNextHandler(handlerB);

        // The request "A" is handled by HandlerA
        handlerA.handleRequest("A");

        // The request "B" is handled by HandlerB
        handlerA.handleRequest("B");
    }
}

