package design.pattern.behavioral.chainofresponsibility.example1;


import java.net.http.HttpRequest;

public interface RequestHandler {
    void setNextHandler(RequestHandler handler);
    void handleRequest(HttpRequest request);
}

