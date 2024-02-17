package basics.multithreading;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolatileCounter {

  private volatile int count = 0;

  public static void main(String[] args) throws InterruptedException {
    final VolatileCounter counter = new VolatileCounter();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });
    Thread t3 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });

    t1.start();
    t2.start();
    t3.start();
    t1.join();
    t2.join();
    t3.join();
    assertEquals(3000, counter.getCount());
  }

  public void increment() {
    count++;  // Non-atomic operation
  }

  public int getCount() {
    return count;
  }
}
