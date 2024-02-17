package basics.multithreading;

public class Printer implements Runnable {

  private static final Object lock = new Object();
  private static int currentNumber = 1;
  private final int upTo;
  private final int index;
  private final int nThreads;

  Printer(int index, int upTo, int nThreads) {
    this.index = index;
    this.upTo = upTo;
    this.nThreads = nThreads;
  }

  @Override
  public void run() {
    while (true) {
      synchronized (lock) {
        if (currentNumber > upTo) {
          break;
        }
        if (currentNumber % nThreads == index) {
          System.out.println(Thread.currentThread().getName() + " " + currentNumber);
          currentNumber++;
          lock.notifyAll();
        } else {
          try {
            lock.wait();
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
            ex.printStackTrace();
          }
        }
      }
    }
  }
}
