package design.pattern.creational.abstractfactory;

public class MySqlConnectionFactory implements ConnectionFactory {
    @Override
    public Connection getConnection() {
        return new MySqlConnection();
    }
}
