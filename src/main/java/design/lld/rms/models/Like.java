package design.lld.rms.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "reelId", nullable = false)
    private Reel reel;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
}
