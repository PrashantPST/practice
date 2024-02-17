package design.lld.youtube;

import java.util.concurrent.ConcurrentLinkedQueue;

public class VideoProcessingQueue {
  private final ConcurrentLinkedQueue<VideoProcessingJob> queue;

  public VideoProcessingQueue() {
    this.queue = new ConcurrentLinkedQueue<>();
  }

  public void addJob(VideoProcessingJob job) {
    queue.offer(job); // Adds the job to the queue
  }

  // Method to process the queue, possibly in a separate thread or service
  public void processQueue() {
    while (!queue.isEmpty()) {
      VideoProcessingJob job = queue.poll(); // Retrieves and removes the head of the queue
      if (job != null) {
        job.processVideo(); // Process the video
      }
    }
  }
}

