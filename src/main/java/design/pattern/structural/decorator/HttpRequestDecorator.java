package design.pattern.structural.decorator;

public abstract class HttpRequestDecorator implements HttpRequest {

  protected HttpRequest request;

  public HttpRequestDecorator(HttpRequest request) {
    this.request = request;
  }

  public void execute() {
    request.execute();
  }
}

