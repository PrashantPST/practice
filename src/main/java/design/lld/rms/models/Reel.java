package design.lld.rms.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reels")
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reelId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String title;
    private String description;
    private Date dateCreated;

    @OneToMany(mappedBy = "reel")
    private List<MediaItem> mediaItems;

    // Constructors, getters, and setters
}

