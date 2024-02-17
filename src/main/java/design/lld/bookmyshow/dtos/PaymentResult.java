package design.lld.bookmyshow.dtos;


import lombok.Getter;

@Getter
public class PaymentResult {
  private boolean successful;
  private String errorMessage;
}
