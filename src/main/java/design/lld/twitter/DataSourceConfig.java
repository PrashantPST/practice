package design.lld.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${primary.datasource.url}")
    private String primaryDbUrl;

    @Value("${primary.datasource.username}")
    private String primaryDbUsername;

    @Value("${primary.datasource.password}")
    private String primaryDbPassword;

    @Value("${replica.datasource.url}")
    private String replicaDbUrl;

    @Value("${replica.datasource.username}")
    private String replicaDbUsername;

    @Value("${replica.datasource.password}")
    private String replicaDbPassword;

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(primaryDbUrl);
        dataSource.setUsername(primaryDbUsername);
        dataSource.setPassword(primaryDbPassword);
        return dataSource;
    }

    @Bean
    public DataSource replicaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(replicaDbUrl);
        dataSource.setUsername(replicaDbUsername);
        dataSource.setPassword(replicaDbPassword);
        return dataSource;
    }
}


