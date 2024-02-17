package design.lld.cofeemachine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CoffeeMachineDriver {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Define ingredients
        Ingredient hotWater = new Ingredient("hot_water");
        Ingredient hotMilk = new Ingredient("hot_milk");
        Ingredient teaLeavesSyrup = new Ingredient("tea_leaves_syrup");
        Ingredient gingerSyrup = new Ingredient("ginger_syrup");
        Ingredient sugarSyrup = new Ingredient("sugar_syrup");
        Ingredient greenMixture = new Ingredient("green_mixture"); // Not available

        // Set up the initial ingredients in the machine
        Map<Ingredient, Integer> initialIngredients = new HashMap<>();
        initialIngredients.put(hotWater, 500);
        initialIngredients.put(hotMilk, 500);
        initialIngredients.put(teaLeavesSyrup, 100);
        initialIngredients.put(gingerSyrup, 100);
        initialIngredients.put(sugarSyrup, 100);
        // Note: Green mixture is not added

        // Initialize CoffeeMachine with 4 outlets
        CoffeeMachine chaiPointMachine = new CoffeeMachine(4, initialIngredients);

        // Define beverages
        Map<Ingredient, Integer> hotTeaRecipe = new HashMap<>();
        hotTeaRecipe.put(hotWater, 200);
        hotTeaRecipe.put(hotMilk, 100);
        hotTeaRecipe.put(teaLeavesSyrup, 30);
        hotTeaRecipe.put(gingerSyrup, 10);
        hotTeaRecipe.put(sugarSyrup, 10);
        Beverage hotTea = new Beverage("Hot Tea", hotTeaRecipe);

        Map<Ingredient, Integer> hotCoffeeRecipe = new HashMap<>();
        hotCoffeeRecipe.put(hotWater, 100);
        hotCoffeeRecipe.put(hotMilk, 400);
        hotCoffeeRecipe.put(teaLeavesSyrup, 30);
        hotCoffeeRecipe.put(sugarSyrup, 50);
        Beverage hotCoffee = new Beverage("Hot Coffee", hotCoffeeRecipe);

        Map<Ingredient, Integer> greenTeaRecipe = new HashMap<>();
        greenTeaRecipe.put(hotWater, 100);
        greenTeaRecipe.put(greenMixture, 30); // Not available
        Beverage greenTea = new Beverage("Green Tea", greenTeaRecipe);

        Map<Ingredient, Integer> blackTeaRecipe = new HashMap<>();
        blackTeaRecipe.put(hotWater, 300); // Not enough hot water available
        blackTeaRecipe.put(teaLeavesSyrup, 30);
        blackTeaRecipe.put(sugarSyrup, 50);
        Beverage blackTea = new Beverage("Black Tea", blackTeaRecipe);

        // Simulate serving beverages
        Future<String> hotTeaResult = chaiPointMachine.serveBeverage(hotTea);
        Future<String> hotCoffeeResult = chaiPointMachine.serveBeverage(hotCoffee);
        Future<String> greenTeaResult = chaiPointMachine.serveBeverage(greenTea);
        Future<String> blackTeaResult = chaiPointMachine.serveBeverage(blackTea);

        // Output the results
        System.out.println(hotTeaResult.get());
        System.out.println(hotCoffeeResult.get());
        System.out.println(greenTeaResult.get());
        System.out.println(blackTeaResult.get());
    }
}


