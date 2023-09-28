package design.lld.splitwise;

import design.lld.splitwise.enums.ExpenseType;
import design.lld.splitwise.models.expense.EqualExpense;
import design.lld.splitwise.models.expense.ExactExpense;
import design.lld.splitwise.models.expense.Expense;
import design.lld.splitwise.models.expense.ExpenseMeta;
import design.lld.splitwise.models.expense.PercentExpense;
import design.lld.splitwise.models.split.PercentSplit;
import design.lld.splitwise.models.split.Split;
import design.lld.splitwise.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private static ExpenseManager expenseManager;
    public List<Expense> expenses = new ArrayList<>();
    public Map<String, User> users = new HashMap<>();
    public Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    private ExpenseManager() {
    }

    public static ExpenseManager getExpenseManager() {
        if (expenseManager == null) {
            expenseManager = new ExpenseManager();
        }
        return expenseManager;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits,
                           ExpenseMeta expenseMetadata) {
        Expense expense = createExpense(expenseType, amount, users.get(paidBy), splits, expenseMetadata);
        expenses.add(expense);
        // System.out.println(expenses);
        assert expense != null;
        for (Split split : expense.getSplits()) {
            System.out.println(split);
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = balanceSheet.get(paidBy);
            balances.putIfAbsent(paidTo, 0.0);
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());
            balances = balanceSheet.get(paidTo);
            balances.putIfAbsent(paidBy, 0.0);
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalanceForAUser(String userId) {
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }
    }

    public void showBalancesForAll() {
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = users.get(user1).getName();
        String user2Name = users.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

    private Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits,
                                        ExpenseMeta expenseMetadata) {
        switch (expenseType) {
            case EXACT -> {
                return new ExactExpense(amount, paidBy, splits, expenseMetadata);
            }
            case PERCENT -> {
                splits.forEach(split -> {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount * percentSplit.getPercent()) / 100.0);
                });
                return new PercentExpense(amount, paidBy, splits, expenseMetadata);
            }
            case EQUAL -> {
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
                splits.forEach(split -> split.setAmount(splitAmount));
                splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
                return new EqualExpense(amount, paidBy, splits, expenseMetadata);
            }
            default -> {
                return null;
            }
        }
    }
}
