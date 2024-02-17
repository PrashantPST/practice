package design.lld.atm.state;

import design.lld.atm.ATM;
import design.lld.atm.strategy.Transaction;

public interface ATMState {
    void insertCard(ATM atm);
    void enterPin(ATM atm, int pin);
    void requestTransaction(ATM atm, Transaction transaction);
    void ejectCard(ATM atm);
}
