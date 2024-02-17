package design.lld.urlshortener.dtos.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShortenURLRequest {

  private String apiDevKey;
  private String originalUrl;
  private String customAlias;
  private LocalDate expiryDate;
}
