package dsa;

import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> max;
    PriorityQueue<Integer> min;

    MedianFinder() {
        max = new PriorityQueue<>((a, b) -> b - a);
        min = new PriorityQueue<>();
    }

    /*
    O(logn) TC and O(n) SC
     */
    public void addNum(int num) {
        if (max.isEmpty() || num <= max.peek() || (!min.isEmpty() && num < min.peek())) {
            max.add(num);
        } else {
            min.add(num);
        }
        if (max.size() - min.size() > 1) {
            min.add(max.poll());
        } else if (min.size() - max.size() > 1) {
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
