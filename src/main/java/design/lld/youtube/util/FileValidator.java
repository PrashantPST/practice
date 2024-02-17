package design.lld.youtube.util;

import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

  private static final long MAX_FILE_SIZE = 10485760; // For example, 10 MB
  private static final Set<String> ALLOWED_MIME_TYPES = Set.of("video/mp4", "video/mpeg", "video/quicktime"); // Add more as needed

  public static boolean isValidFile(MultipartFile file) {
    return file != null &&
        ALLOWED_MIME_TYPES.contains(file.getContentType()) &&
        file.getSize() <= MAX_FILE_SIZE;
  }
}

