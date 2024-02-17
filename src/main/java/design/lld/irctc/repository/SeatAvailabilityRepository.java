package design.lld.irctc.repository;

import design.lld.irctc.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Long> {

    List<SeatAvailability> findByTrainIdAndTravelDate(Long trainId, LocalDate travelDate);

    SeatAvailability findByTrainIdAndTravelDateAndClassTypeAndBerthType(Long trainId, LocalDate travelDate,
                                                                        String classType, String berthType);
}

