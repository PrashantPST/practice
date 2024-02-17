package design.lld.bookmyshow.exceptions;

public class PaymentFailedException extends RuntimeException {

  public PaymentFailedException(String errorMessage) {
    super(errorMessage);
  }
}
