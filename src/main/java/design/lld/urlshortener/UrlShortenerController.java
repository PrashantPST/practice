package design.lld.urlshortener;

import design.lld.urlshortener.dtos.request.DeleteURLRequest;
import design.lld.urlshortener.dtos.request.ShortenURLRequest;
import design.lld.urlshortener.service.URLShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlShortenerController {

  @Autowired
  private final URLShorteningService urlShorteningService;

  public UrlShortenerController(URLShorteningService urlShorteningService) {
    this.urlShorteningService = urlShorteningService;
  }

  @PostMapping
  public ResponseEntity<String> createShortURL(@RequestBody ShortenURLRequest request) {
    if (!isValidApiDevKey(request.getApiDevKey())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Developer Key");
    }
    try {
      Url shortUrl = urlShorteningService.shortenUrl(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl.getShortUrl());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error while shortening URL");
    }
  }

  @GetMapping("/{shortUrl}")
  public RedirectView getOriginalURL(@PathVariable String shortUrl) {
    String originalUrl = urlShorteningService.getOriginalUrl(shortUrl);
    return new RedirectView(Objects.requireNonNullElse(originalUrl, "/error-url-not-found"));
  }

  @DeleteMapping("/{shortUrl}")
  public ResponseEntity<?> deleteShortURL(@RequestBody DeleteURLRequest request,
      @PathVariable String shortUrl) {
    if (!isValidApiDevKey(request.getApiDevKey())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Developer Key");
    }
    boolean isDeleted = urlShorteningService.deleteUrl(shortUrl);
    if (isDeleted) {
      return ResponseEntity.ok().body("URL deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
    }
  }

  private boolean isValidApiDevKey(String apiDevKey) {
    // Implement your logic to validate the API developer key
    return true;
  }
}