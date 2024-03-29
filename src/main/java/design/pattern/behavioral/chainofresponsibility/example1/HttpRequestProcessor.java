package design.pattern.behavioral.chainofresponsibility.example1;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Optional;

public class HttpRequestProcessor {
    public static void main(String[] args) {
        RequestHandler authHandler = new AuthenticationHandler();
        RequestHandler authzHandler = new AuthorizationHandler();

        authHandler.setNextHandler(authzHandler);

        HttpRequest request = new HttpRequest() {
            @Override
            public Optional<BodyPublisher> bodyPublisher() {
                return Optional.empty();
            }

            @Override
            public String method() {
                return null;
            }

            @Override
            public Optional<Duration> timeout() {
                return Optional.empty();
            }

            @Override
            public boolean expectContinue() {
                return false;
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public Optional<HttpClient.Version> version() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }
        };
        // Initialize HttpRequest object
        authHandler.handleRequest(request);
    }
}

