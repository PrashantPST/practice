package design.lld.cofeemachine;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CoffeeMachine {
    private final Map<Ingredient, Integer> availableIngredients;
    private final ExecutorService outletPool;

    public CoffeeMachine(int numberOfOutlets, Map<Ingredient, Integer> initialIngredients) {
        this.availableIngredients = initialIngredients;
        this.outletPool = Executors.newFixedThreadPool(numberOfOutlets);
    }

    public Future<String> serveBeverage(Beverage beverage) {
        return outletPool.submit(() -> {
            synchronized (this) {
                // Check if all ingredients are available in required quantities
                for (Map.Entry<Ingredient, Integer> entry : beverage.getRecipe().entrySet()) {
                    Ingredient ingredient = entry.getKey();
                    int requiredQuantity = entry.getValue();
                    if (!availableIngredients.containsKey(ingredient) || availableIngredients.get(ingredient) < requiredQuantity) {
                        return "Cannot make " + beverage.getName() + ": Insufficient " + ingredient.getName();
                    }
                }
                // Deduct the used ingredients from the available stock
                for (Map.Entry<Ingredient, Integer> entry : beverage.getRecipe().entrySet()) {
                    Ingredient ingredient = entry.getKey();
                    int usedQuantity = entry.getValue();
                    availableIngredients.merge(ingredient, -usedQuantity, Integer::sum);
                }
            }
            return beverage.getName() + " is prepared";
        });
    }

    public void refillIngredient(Ingredient ingredient, int quantity) {
        availableIngredients.merge(ingredient, quantity, Integer::sum);
    }

}

