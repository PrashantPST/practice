package design.pattern.behavioral.observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Product {
    private final String name;
    private boolean available;
    private final List<Observer> observers;

    public Product(String name) {
        this.name = name;
        this.available = false;
        this.observers = new ArrayList<>();
    }

    public void setAvailability(boolean available) {
        this.available = available;
        if (available) {
            notifyObservers();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

