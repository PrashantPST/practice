package design.lld.youtube.model;

import java.util.List;
import lombok.Getter;


@Getter
public class VideoMetadata {

  private String videoId;
  private String title;
  private String description;
  private List<String> tags;
  private String category;
  private boolean isPublic;

  // Constructors
  public VideoMetadata() {
  }

  public VideoMetadata(String title, String description, List<String> tags, String category,
      boolean isPublic) {
    this.title = title;
    this.description = description;
    this.tags = tags;
    this.category = category;
    this.isPublic = isPublic;
  }
  @Override
  public String toString() {
    return "VideoMetadata{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", tags=" + tags +
        ", category='" + category + '\'' +
        ", isPublic=" + isPublic +
        '}';
  }
}

