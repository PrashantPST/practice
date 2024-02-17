package design.lld.youtube.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "video_comments", indexes = {
    @Index(name = "idx_video_id", columnList = "videoId")
})
public class VideoComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private Long videoId;

  @Column(nullable = false)
  private Long userId;

  @Column(length = 500) // Adjust length as needed
  private String commentText;

  @Column(nullable = false)
  private LocalDateTime timestamp;

  // Constructors, getters, and setters

  public VideoComment() {
    // Default constructor
  }
}
