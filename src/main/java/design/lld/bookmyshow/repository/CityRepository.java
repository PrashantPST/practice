package design.lld.bookmyshow.repository;

import design.lld.bookmyshow.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
  City findById(long id);
}
