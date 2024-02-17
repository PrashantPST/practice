package design.lld.urlshortener;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "urls")
@Getter
public class Url {

  @Id
  private final String id;

  @Field("original_url")
  private final String originalUrl;

  @Indexed
  @Field("short_url")
  private final String shortUrl;

  @Field("expiry_date")
  private final LocalDate expiryDate;

  // Assuming User is another document. Store only the ID if it's a separate collection
  private final String userId;

  public Url(String originalUrl, String shortUrl, LocalDate expiryDate, String userId) {
    this.id = UUID.randomUUID().toString();
    this.originalUrl = originalUrl;
    this.shortUrl = shortUrl;
    this.expiryDate = expiryDate;
    this.userId = userId;
  }
}


