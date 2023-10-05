package basics;

public class Printer implements Runnable {

    private static final Object lock = new Object();
    private final int UP_TO;
    private static int currentNumber = 1;
    private final int index;
    private final int nThreads;

    Printer(int index, int upTo, int nThreads) {
        this.index = index;
        this.UP_TO = upTo;
        this.nThreads = nThreads;
    }
    @Override
    public void run() {
        synchronized (lock) {
            while (currentNumber <= UP_TO) {
                while (currentNumber % nThreads != index) {
                    try {
                        lock.wait();
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                if (currentNumber <= UP_TO) {
                    System.out.println(Thread.currentThread().getName() + " " + currentNumber);
                    currentNumber++;
                }
                lock.notifyAll();
            }
        }
    }
}
