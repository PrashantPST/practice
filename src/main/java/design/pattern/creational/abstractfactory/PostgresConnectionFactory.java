package design.pattern.creational.abstractfactory;

public class PostgresConnectionFactory implements ConnectionFactory {
    @Override
    public Connection getConnection() {
        return new PostgresConnection();
    }
}
