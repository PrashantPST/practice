package dsa.design;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    public final PriorityQueue<Integer> max;
    public final PriorityQueue<Integer> min;

    public MedianFinder() {
        max = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
        min = new PriorityQueue<>(); // Min heap
    }

    /*
    O(logn) TC and O(n) SC
     */
    public void addNum(int num) {
        if (max.isEmpty() || num <= max.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
        if (max.size() > 1 + min.size()) {
            min.add(max.poll());
        } else if (min.size() > 1 + max.size()) {
            max.add(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() == min.size()) {
            return (double) (max.peek() + min.peek()) / 2;
        } else if (max.size() > min.size()) {
            return max.peek();
        }
        return min.peek();
    }
}
