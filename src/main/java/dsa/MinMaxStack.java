package dsa;

import lombok.AllArgsConstructor;

import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving the minimum/maximum element in constant time
 */
public class MinMaxStack {
    Stack<Item> s = new Stack<>();

    public int peek() {
        return s.peek().value;
    }

    public int pop() {
        return s.pop().value;
    }

    public void push(Integer number) {
        int min;
        int max;
        if (s.isEmpty()) {
            s.push(new Item(number, number, number));
        }
        else {
            Item top = s.peek();
            min = Math.min(number, top.min);
            max = Math.max(number, top.max);
            s.push(new Item(number, min, max));
        }
    }

    public int getMin() {
        return s.peek().min;
    }

    public int getMax() {
        return s.peek().max;
    }

    @AllArgsConstructor
    static class Item {
        int value;
        int min;
        int max;
    }
}
