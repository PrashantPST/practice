package design.pattern.structural.proxy.example1;

public class DatabaseClient {
    public static void main(String[] args) {
        RealDatabaseConnection realConnection = new RealDatabaseConnection();
        DatabaseConnection proxyConnection = new LoggingDatabaseConnectionProxy(realConnection);

        proxyConnection.executeQuery("SELECT * FROM users");
    }
}


