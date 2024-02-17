package design.lld.atm.strategy;

import design.lld.atm.Account;

public class BalanceCheckTransaction implements Transaction {

    @Override
    public void execute(Account account) {
        double balance = account.checkBalance();
        System.out.println("Current balance: $" + balance);
    }
}
