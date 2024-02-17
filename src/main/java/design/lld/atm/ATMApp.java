package design.lld.atm;

import design.lld.atm.strategy.BalanceCheckTransaction;
import design.lld.atm.strategy.Transaction;
import design.lld.atm.strategy.WithdrawalTransaction;

public class ATMApp {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Account account = new Account("12345678", 1234, 1000.0);

        atm.insertCard(account);
        atm.enterPin(1234);

        Transaction withdrawal = new WithdrawalTransaction(200);
        atm.performTransaction(withdrawal);

        Transaction balanceCheck = new BalanceCheckTransaction();
        atm.performTransaction(balanceCheck);

        atm.ejectCard();
    }
}

