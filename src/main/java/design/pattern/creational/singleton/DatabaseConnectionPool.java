package design.pattern.creational.singleton;


/**
 * By using double-checked locking and making the instance variable volatile, we can ensure that our
 * DatabaseConnectionPool class is thread-safe and can be safely used in a multithreaded
 * environment. 1. Database Connection: DatabaseConnectionPool to ensure that there is only one
 * connection to the database throughout the application's lifetime. 2. Configuration Management: a
 * single source of configuration information that can be accessed from various parts of the
 * application 3. Logging: all log messages are written to the same location, and that there is only
 * one instance of the logging system. 4. Caching: only one instance of the cache and that all parts
 * of the application can access the same cached data. 5. Thread Pools: only one instance of the
 * thread pool
 */
public class DatabaseConnectionPool {

  private volatile static DatabaseConnectionPool instance;

  private DatabaseConnectionPool() {
    System.out.println("Single instantiation of database connection pool");
    if (instance != null) {
      throw new RuntimeException("Please don't use reflection to get an instance here.");
    }
  }

  /*
       Using synchronized every time while creating the singleton object is expensive and may decrease the
       performance of your program
  */
  public static DatabaseConnectionPool getInstance() {
    if (instance == null) {
      // t1, t2, t3, t4, t5
      synchronized (DatabaseConnectionPool.class) {

        // t1
        if (instance == null) { // double checking
          instance = new DatabaseConnectionPool();
        }
      }
      // t1
    }
    return instance;
  }
}