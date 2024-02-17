package design.lld.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class TweetService {
    private final JdbcTemplate writeJdbcTemplate;
    private final JdbcTemplate readJdbcTemplate;

    @Autowired
    public TweetService(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                        @Qualifier("replicaDataSource") DataSource replicaDataSource) {
        this.writeJdbcTemplate = new JdbcTemplate(primaryDataSource);
        this.readJdbcTemplate = new JdbcTemplate(replicaDataSource);
    }

    public void postTweet(String data) {
        writeJdbcTemplate.update("INSERT INTO my_table (data) VALUES (?)", data);
    }

    public String fetchTweet() {
        return readJdbcTemplate.queryForObject("SELECT data FROM my_table", String.class);
    }
}
