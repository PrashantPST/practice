package basics.multithreading;

public class PrinterTest {

  public static void main(String[] args) {
    int nThreads = 3; // Number of threads
    int upTo = 40; // Print numbers up to this value

    Thread[] threads = new Thread[nThreads];
    threads[0] = new Thread(new Printer(0, upTo, nThreads), "Thread-3");
    threads[1] = new Thread(new Printer(1, upTo, nThreads), "Thread-1");
    threads[2] = new Thread(new Printer(2, upTo, nThreads), "Thread-2");

    for (int i = 0; i < nThreads; i++) {
      threads[i].start();
    }
    // Wait for all threads to finish
    for (int i = 0; i < nThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

