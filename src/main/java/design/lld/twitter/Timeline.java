package design.lld.twitter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "timelines")
public class Timeline {
    @Id
    private String userId;
    private List<Tweet> tweets;
}
