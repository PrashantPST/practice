package design.lld.twitter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "followerId", referencedColumnName = "userId")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followingId", referencedColumnName = "userId")
    private User following;

    // Standard getters and setters
}
