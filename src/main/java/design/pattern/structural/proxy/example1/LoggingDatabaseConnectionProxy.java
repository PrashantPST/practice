package design.pattern.structural.proxy.example1;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoggingDatabaseConnectionProxy implements DatabaseConnection {

    private final RealDatabaseConnection realConnection;

    @Override
    public void executeQuery(String query) {
        logQuery(query);
        realConnection.executeQuery(query);
    }

    private void logQuery(String query) {
        System.out.println("Log: Executing query - " + query);
        // Additional logging logic can be implemented here
    }
}

