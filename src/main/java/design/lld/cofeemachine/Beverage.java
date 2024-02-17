package design.lld.cofeemachine;

import lombok.Getter;

import java.util.Map;


@Getter
public class Beverage {

    private String name;
    private Map<Ingredient, Integer> recipe; // Ingredient and its quantity for this beverage

    public Beverage(String name, Map<Ingredient, Integer> recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    // Getters
}

