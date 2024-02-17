package design.pattern.structural.decorator;

public class AuthHttpRequestDecorator extends HttpRequestDecorator {

  public AuthHttpRequestDecorator(HttpRequest request) {
    super(request);
  }

  @Override
  public void execute() {
    System.out.println("Adding Authentication Header");
    super.execute();
  }
}

