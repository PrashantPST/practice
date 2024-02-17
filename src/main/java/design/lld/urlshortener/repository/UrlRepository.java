package design.lld.urlshortener.repository;

import design.lld.urlshortener.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, String> {

  Url findByShortUrl(String shortUrl);

  boolean existsByShortUrl(String shortUrl);
}

