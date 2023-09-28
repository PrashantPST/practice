package design.pattern.creational.abstractfactory;

public class PostgresConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL...");
    }
}
