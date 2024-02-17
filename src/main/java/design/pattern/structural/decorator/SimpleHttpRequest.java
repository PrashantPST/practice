package design.pattern.structural.decorator;

public class SimpleHttpRequest implements HttpRequest {
    @Override
    public void execute() {
        System.out.println("Executing HTTP request");
    }
}

