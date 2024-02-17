package design.pattern.structural.proxy.example1;

public class RealDatabaseConnection implements DatabaseConnection {

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
        // In a real application, database query execution logic would be here
    }
}

