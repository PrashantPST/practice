package design.lld.rms.models;

import javax.persistence.*;

@Entity
@Table(name = "media_items")
public class MediaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaItemId;

    @ManyToOne
    @JoinColumn(name = "reelId", nullable = false)
    private Reel reel;

    private String url;
    private String type;
    private int orderInReel;

    // Constructors, getters, and setters
}

