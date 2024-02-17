package dsa.design;

import java.util.PriorityQueue;

public class TopKSumStream {

  private final PriorityQueue<Integer> minHeap;
  private final int k;
  private int sum;

  public TopKSumStream(int k) {
    this.minHeap = new PriorityQueue<>();
    this.k = k;
    this.sum = 0;
  }

  /*
      O(log K)
   */
  public void add(int number) {
    if (minHeap.size() < k) {
      minHeap.add(number);
      sum += number;
    } else {
      if (number > minHeap.peek()) {
        sum -= minHeap.poll();
        minHeap.add(number);
        sum += number;
      }
    }
  }

  // Additional method to handle stream input
  public void addStream(int[] stream) {
    for (int num : stream) {
      add(num);
    }
  }

  public int getSum() {
    return sum;
  }
}

