package design.lld.atm.strategy;

import design.lld.atm.Account;

public class WithdrawalTransaction implements Transaction {
    private final double amount;

    public WithdrawalTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Account account) {
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Withdrawal of $" + amount + " completed.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

