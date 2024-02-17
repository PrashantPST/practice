package design.pattern.structural.decorator;

public class LoggingHttpRequestDecorator extends HttpRequestDecorator {
    public LoggingHttpRequestDecorator(HttpRequest request) {
        super(request);
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Logging request");
    }
}

