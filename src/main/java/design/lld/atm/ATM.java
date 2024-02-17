package design.lld.atm;

import design.lld.atm.state.ATMState;
import design.lld.atm.state.IdleState;
import design.lld.atm.strategy.Transaction;
import lombok.Getter;


@Getter
public class ATM {

    private ATMState currentState;
    private Account currentAccount;

    public ATM() {
        this.currentState = new IdleState();
    }

    public void setState(ATMState newState) {
        this.currentState = newState;
    }

    public void insertCard(Account account) {
        this.currentAccount = account;
        currentState.insertCard(this);
    }

    public void enterPin(int pin) {
        currentState.enterPin(this, pin);
    }
    public void performTransaction(Transaction transaction) {
        currentState.requestTransaction(this, transaction);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
        this.currentAccount = null;
        setState(new IdleState());
    }

    // State classes need to access these methods
    public void withdraw(double amount) {
        if (currentAccount != null) {
            currentAccount.withdraw(amount);
        }
    }

    public void deposit(double amount) {
        if (currentAccount != null) {
            currentAccount.deposit(amount);
        }
    }

    public double checkBalance() {
        if (currentAccount != null) {
            return currentAccount.checkBalance();
        }
        return 0;
    }

    // Additional methods as required...
}


