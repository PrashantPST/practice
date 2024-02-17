package design.lld.bookmyshow.services;

import design.lld.bookmyshow.dtos.PaymentResult;
import design.lld.splitwise.models.User;
import java.math.BigDecimal;

public interface PaymentService {
  PaymentResult processPayment(User user, BigDecimal amount);
}
