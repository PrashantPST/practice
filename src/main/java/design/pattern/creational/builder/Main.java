package design.pattern.creational.builder;

public class Main {

  public static void main(String[] args) {
    BankAccount account = new BankAccount.BankAccountBuilder("123456", "John Doe")
        .withEmail("john.doe@example.com")
        .withBalance(1000)
        .build();
    System.out.println(account);
  }
}
