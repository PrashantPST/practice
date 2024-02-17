package design.lld.rms.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "reelId", nullable = false)
    private Reel reel;

    @Column(nullable = false)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
}
