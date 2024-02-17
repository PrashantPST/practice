package design.lld.youtube.controller;


import design.lld.youtube.model.VideoMetadata;
import design.lld.youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoController {

  @Autowired
  private final VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }
  @PostMapping("/upload")
  public ResponseEntity<?> uploadVideoChunk(
      @RequestParam("fileChunk") MultipartFile fileChunk,
      @RequestParam("metadata") VideoMetadata metadata,
      @RequestParam("chunkNumber") int chunkNumber,
      @RequestParam("totalChunks") int totalChunks,
      Authentication authentication) {

    return videoService.processVideoChunkUpload(fileChunk, metadata, chunkNumber, totalChunks, authentication);
  }


  @GetMapping("/{videoId}")
  public ResponseEntity<?> watchVideo(@PathVariable String videoId,
      @RequestParam(required = false) String quality) {
    return videoService.getVideoStream(videoId, quality);
  }
}

