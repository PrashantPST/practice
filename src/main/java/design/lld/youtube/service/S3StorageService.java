package design.lld.youtube.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3StorageService {

  private final S3Client s3Client;
  private final String bucketName = "your-bucket-name";

  public S3StorageService() {
    this.s3Client = S3Client.builder()
        .credentialsProvider(DefaultCredentialsProvider.create())
        .region(Region.of("your-region")) // e.g., Region.US_EAST_1
        .build();
  }

  public String saveFile(MultipartFile file) throws IOException {
    String fileName = generateFileName(file);
    s3Client.putObject(
        PutObjectRequest.builder()
            .bucket(bucketName)
            .key(fileName)
            .build(),
        software.amazon.awssdk.core.sync.RequestBody.fromInputStream(file.getInputStream(),
            file.getSize())
    );
    return fileName; // Or return a full URL/path to the file
  }

  private String generateFileName(MultipartFile file) {
    // Implement a way to generate a unique file name
    return System.currentTimeMillis() + "_" + file.getOriginalFilename();
  }
}

