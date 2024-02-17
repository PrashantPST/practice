package design.lld.atm.state;

import design.lld.atm.ATM;
import design.lld.atm.strategy.Transaction;

public class TransactionState implements ATMState {

    @Override
    public void insertCard(ATM atm) {
        System.out.println("Transaction in progress. Please wait.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("PIN already entered.");
    }

    @Override
    public void requestTransaction(ATM atm, Transaction transaction) {
        System.out.println("Processing transaction...");
        transaction.execute(atm.getCurrentAccount());
        System.out.println("Transaction completed.");
        atm.setState(new IdleState());
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Please complete the transaction or cancel it before ejecting the card.");
    }
}

