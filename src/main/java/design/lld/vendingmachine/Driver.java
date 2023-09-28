package design.lld.vendingmachine;

import design.lld.vendingmachine.inventory.Product;

public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Product hersheys = new Product("hersheys", 1, 5.0);
        Product biskFarm = new Product("biskFarm", 2, 2.0);
        // insert 3 hersheys
        for (int i = 1; i <= 3; i++) {
            vendingMachine.addProduct(hersheys);
        }
        for (int i = 1; i <= 3; i++) {
            vendingMachine.addProduct(biskFarm);
        }
        vendingMachine.insertCoin(5.0);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.insertCoin(3.0);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.pressButton(1);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.insertCoin(5.0);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.pressButton(1);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.insertCoin(7.0);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
        vendingMachine.pressButton(2);
        System.out.println("Total balance = "+ vendingMachine.getAmount());
    }
}
