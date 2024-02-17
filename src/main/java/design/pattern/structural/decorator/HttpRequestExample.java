package design.pattern.structural.decorator;

public class HttpRequestExample {
    public static void main(String[] args) {

        HttpRequest request = new SimpleHttpRequest();
        request = new AuthHttpRequestDecorator(request);
        request = new LoggingHttpRequestDecorator(request);
        request.execute();
    }
}
