package design.lld.youtube.service;


import design.lld.youtube.VideoProcessingJob;
import design.lld.youtube.VideoProcessingQueue;
import design.lld.youtube.model.User;
import design.lld.youtube.model.VideoMetadata;
import design.lld.youtube.repository.VideoMetadataRepository;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Service
public class VideoService {
  private final ConcurrentHashMap<String, List<MultipartFile>> videoChunksMap = new ConcurrentHashMap<>();

  @Autowired
  private final AuthenticationService authenticationService;
  @Autowired
  private final S3StorageService s3StorageService;
  @Autowired
  private final VideoProcessingQueue videoProcessingQueue;
  @Autowired
  private final VideoMetadataRepository videoMetadataRepository;

  public VideoService(AuthenticationService authenticationService,
      S3StorageService s3StorageService, VideoProcessingQueue videoProcessingQueue,
      VideoMetadataRepository videoMetadataRepository) {
    this.authenticationService = authenticationService;
    this.s3StorageService = s3StorageService;
    this.videoProcessingQueue = videoProcessingQueue;
    this.videoMetadataRepository = videoMetadataRepository;
  }

  public ResponseEntity<?> processVideoChunkUpload(MultipartFile fileChunk, VideoMetadata metadata,
      int chunkNumber, int totalChunks,
      Authentication authentication) {
    try {
      // Validate and authenticate...
      User user = authenticationService.authenticateUser(authentication);

      // Store chunk
      String videoId = metadata.getVideoId(); // Ensure metadata contains a unique video ID
      videoChunksMap.computeIfAbsent(videoId, k -> new ArrayList<>()).add(fileChunk);

      // Check if all chunks have been received
      if (videoChunksMap.get(videoId).size() == totalChunks) {
        // Reassemble and process the video
        MultipartFile completeVideo = reassembleVideo(videoId, videoChunksMap.get(videoId));
        String tempStoragePath = s3StorageService.saveFile(completeVideo);
        videoProcessingQueue.addJob(new VideoProcessingJob(tempStoragePath, metadata, user));

        // Clear the stored chunks for this video
        videoChunksMap.remove(videoId);

        return ResponseEntity.ok().body("Video processing started");
      }

      return ResponseEntity.ok().body("Chunk " + chunkNumber + " of " + totalChunks + " received");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chunk upload failed");
    }
  }

  private MultipartFile reassembleVideo(String videoId, List<MultipartFile> chunks) {
    // Implement logic to reassemble chunks into a single MultipartFile
    // This might involve writing chunks to a temporary file in the correct order
    return null;
  }


  public ResponseEntity<?> getVideoStream(String videoId, String quality) {
    try {
      VideoMetadata videoMetadata = videoMetadataRepository.getVideoMetadata(videoId);
      if (videoMetadata == null) {
        return ResponseEntity.notFound().build();
      }
      StreamingResponseBody stream = outputStream -> streamVideo(videoId, quality, outputStream);
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType("video/mp4"))
          .body(stream);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error watching video");
    }
  }

  private void streamVideo(String videoId, String quality, OutputStream outputStream) {

  }
}

