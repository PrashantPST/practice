package dsa.design;

/*
Design a stack that supports push, pop, top, and retrieving the minimum/maximum element in constant time
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class MinMaxStack {
    private final Deque<Item> stack = new ArrayDeque<>();

    // Returns the top element of the stack without removing it
    public Integer peek() {
        if (stack.isEmpty()) {
            return null; // or throw exception
        }
        return stack.peek().value;
    }

    // Removes and returns the top element of the stack
    public Integer pop() {
        if (stack.isEmpty()) {
            return null; // or throw exception
        }
        return stack.pop().value;
    }

    // Pushes an element onto the stack
    public void push(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null");
        }
        int min = number;
        int max = number;
        if (!stack.isEmpty()) {
            Item top = stack.peek();
            min = Math.min(number, top.min);
            max = Math.max(number, top.max);
        }
        stack.push(new Item(number, min, max));
    }

    // Returns the minimum element in the stack
    public Integer getMin() {
        if (stack.isEmpty()) {
            return null; // or throw exception
        }
        return stack.peek().min;
    }

    // Returns the maximum element in the stack
    public Integer getMax() {
        if (stack.isEmpty()) {
            return null; // or throw exception
        }
        return stack.peek().max;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Helper class to store value, min, and max at each stack level
    private static class Item {
        int value;
        int min;
        int max;

        Item(int value, int min, int max) {
            this.value = value;
            this.min = min;
            this.max = max;
        }
    }
}

