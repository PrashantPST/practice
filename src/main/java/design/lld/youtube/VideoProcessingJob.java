package design.lld.youtube;

import design.lld.youtube.model.User;
import design.lld.youtube.model.VideoMetadata;

public class VideoProcessingJob {
  private String tempStoragePath;
  private VideoMetadata metadata;
  private User user;

  // Constructor
  public VideoProcessingJob(String tempStoragePath, VideoMetadata metadata, User user) {
    this.tempStoragePath = tempStoragePath;
    this.metadata = metadata;
    this.user = user;
  }

  // Getters and Setters
  public String getTempStoragePath() {
    return tempStoragePath;
  }

  public void setTempStoragePath(String tempStoragePath) {
    this.tempStoragePath = tempStoragePath;
  }

  public VideoMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(VideoMetadata metadata) {
    this.metadata = metadata;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  // Additional methods for processing the video can be added here
  // For example, a method to process the video file and update its status
  public void processVideo() {
    // Implement video processing logic here
    // This could include calling external services, transcoding the video, etc.
  }
}

