package design.pattern.creational.builder;


import lombok.ToString;

@ToString
public class BankAccount {

  private final String accountNumber; // Required
  private final String ownerName; // Required
  private final String email; // Optional
  private final double balance; // Optional

  private BankAccount(BankAccountBuilder builder) {
    this.accountNumber = builder.accountNumber;
    this.ownerName = builder.ownerName;
    this.email = builder.email;
    this.balance = builder.balance;
  }

  // Static Builder Class
  public static class BankAccountBuilder {

    private final String accountNumber;
    private final String ownerName;
    private String email;
    private double balance;

    public BankAccountBuilder(String accountNumber, String ownerName) {
      if (accountNumber == null || accountNumber.isEmpty()) {
        throw new IllegalArgumentException("Account number cannot be null or empty");
      }
      if (ownerName == null || ownerName.trim().isEmpty()) {
        throw new IllegalArgumentException("Owner name cannot be null or empty");
      }
      this.accountNumber = accountNumber;
      this.ownerName = ownerName;
    }

    public BankAccountBuilder withEmail(String email) {
      if (!isValidEmail(email)) {
        throw new IllegalArgumentException("Invalid email format");
      }
      this.email = email;
      return this;
    }

    public BankAccountBuilder withBalance(double balance) {
      if (balance < 0) {
        throw new IllegalArgumentException("Balance cannot be negative");
      }
      this.balance = balance;
      return this;
    }

    public BankAccount build() {
      return new BankAccount(this);
    }

    private boolean isValidEmail(String email) {
      // Email validation logic
      return email.contains("@");
    }
  }
}


