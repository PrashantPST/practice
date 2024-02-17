package design.lld.atm.strategy;

import design.lld.atm.Account;

public interface Transaction {
    void execute(Account account);
}
