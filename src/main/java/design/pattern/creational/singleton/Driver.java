package design.pattern.creational.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
            executor.execute(Singleton::getInstance);
            executor.execute(Singleton::getInstance);
            executor.execute(Singleton::getInstance);
            executor.execute(Singleton::getInstance);
            executor.execute(Singleton::getInstance);
            executor.execute(Singleton::getInstance);
        }
}
