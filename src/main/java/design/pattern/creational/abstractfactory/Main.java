package design.pattern.creational.abstractfactory;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        DatabaseType databaseType = DatabaseType.MYSQL;
        ConnectionFactory connectionFactory = getConnectionFactory(databaseType);
        Application application = new Application(connectionFactory);
        application.start();
        connectionFactory = getConnectionFactory(DatabaseType.POSTGRES);
        application = new Application(connectionFactory);
        application.start();
    }

    private static ConnectionFactory getConnectionFactory(DatabaseType databaseType) {
        if (Objects.requireNonNull(databaseType) == DatabaseType.MYSQL) {
            return new MySqlConnectionFactory();
        }
        return new PostgresConnectionFactory();
    }

    private enum DatabaseType {
        MYSQL, POSTGRES;
    }
}
