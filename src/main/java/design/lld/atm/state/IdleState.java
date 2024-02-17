package design.lld.atm.state;

import design.lld.atm.ATM;
import design.lld.atm.strategy.Transaction;

public class IdleState implements ATMState {

    @Override
    public void insertCard(ATM atm) {
        System.out.println("Card inserted.");
        atm.setState(new HasCardState());
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("Please insert a card first.");
    }
    @Override
    public void requestTransaction(ATM atm, Transaction transaction) {
        System.out.println("Please insert a card first.");
    }
    @Override
    public void ejectCard(ATM atm) {
        System.out.println("No card to eject.");
    }
}

