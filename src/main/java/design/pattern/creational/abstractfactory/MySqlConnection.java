package design.pattern.creational.abstractfactory;

public class MySqlConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL...");
    }
}
