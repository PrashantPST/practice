package design.lld.urlshortener.service;

import design.lld.urlshortener.Url;
import design.lld.urlshortener.repository.UrlRepository;
import design.lld.urlshortener.dtos.request.ShortenURLRequest;
import design.lld.urlshortener.exceptions.CustomAliasAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class URLShorteningService {

  private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final AtomicLong counter = new AtomicLong(1000000);
  @Autowired
  private UrlRepository urlRepository;
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  private boolean isValidBase62(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    for (char ch : str.toCharArray()) {
      if (BASE62_CHARS.indexOf(ch) == -1) {
        return false;
      }
    }
    return true;
  }

  /*
      For our requirement, we should use n=7 i.e. the length of short URLs will be 7,
      and we can serve 62^7 ~= 3500 billion URLs.
   */
  private static String toBase62(long number) {
    StringBuilder sb = new StringBuilder();
    while (number > 0) {
      sb.append(BASE62_CHARS.charAt((int) (number % BASE62_CHARS.length())));
      number /= BASE62_CHARS.length();
    }
    // Pad the string to 7 characters
    while (sb.length() < 7) {
      sb.insert(0, '0');
    }
    return sb.reverse().toString();
  }

  public Url shortenUrl(ShortenURLRequest request) {
    String shortUrlKey;
    if (request.getCustomAlias() != null && !request.getCustomAlias().isEmpty()) {
      shortUrlKey = request.getCustomAlias();
      if (!isValidBase62(shortUrlKey) || urlRepository.existsByShortUrl(shortUrlKey)) {
        throw new CustomAliasAlreadyExistsException("Custom alias already in use.");
      }
    } else {
      long uniqueNumber = counter.getAndIncrement();
      shortUrlKey = toBase62(uniqueNumber);
    }
    Url url = new Url(request.getOriginalUrl(), shortUrlKey, request.getExpiryDate(), null);
    urlRepository.save(url);
    return url;
  }

  public String getOriginalUrl(String shortUrl) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    String cachedOriginalUrl = ops.get(shortUrl);
    if (cachedOriginalUrl != null) {
      return cachedOriginalUrl;
    }
    Url url = urlRepository.findByShortUrl(shortUrl);
    if (url != null) {
      ops.set(shortUrl, url.getOriginalUrl());
      return url.getOriginalUrl();
    }
    return null;
  }

  public boolean deleteUrl(String urlKey) {
    return true;
  }

}

