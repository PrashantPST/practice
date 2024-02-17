package design.lld.bookmyshow.services;

import design.lld.bookmyshow.City;
import design.lld.bookmyshow.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<City> getCities() {
    return cityRepository.findAll();
  }
}

