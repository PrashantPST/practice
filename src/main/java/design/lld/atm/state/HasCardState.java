package design.lld.atm.state;

import design.lld.atm.ATM;
import design.lld.atm.strategy.Transaction;

public class HasCardState implements ATMState {

    @Override
    public void insertCard(ATM atm) {
        System.out.println("A card is already inserted.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        if (atm.getCurrentAccount() != null && atm.getCurrentAccount().validatePin(pin)) {
            System.out.println("PIN entered correctly.");
            atm.setState(new TransactionState());
        } else {
            System.out.println("Incorrect PIN. Please try again.");
        }
    }

    @Override
    public void requestTransaction(ATM atm, Transaction transaction) {
        System.out.println("Please enter your PIN first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Card ejected.");
        atm.setState(new IdleState());
    }
}

