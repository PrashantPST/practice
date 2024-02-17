package design.pattern.creational.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(DatabaseConnectionPool::getInstance);
        executor.execute(DatabaseConnectionPool::getInstance);
        executor.execute(DatabaseConnectionPool::getInstance);
        executor.execute(DatabaseConnectionPool::getInstance);
        executor.execute(DatabaseConnectionPool::getInstance);
        executor.execute(DatabaseConnectionPool::getInstance);

        executor.shutdown(); // Shutdown the executor after all tasks have been executed
    }
}