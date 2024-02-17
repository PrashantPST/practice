package design.pattern.behavioral.chainofresponsibility;


import lombok.Setter;

@Setter
public abstract class Handler {

    protected Handler nextHandler;
    public abstract void handleRequest(String request);
}

