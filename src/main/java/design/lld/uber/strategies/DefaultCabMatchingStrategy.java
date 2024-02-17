package design.lld.uber.strategies;

import design.lld.uber.models.Cab;
import design.lld.uber.models.Location;
import design.lld.uber.models.Rider;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

  @Override
  public Optional<Cab> matchCabToRider(
      @NonNull final Rider rider,
      @NonNull final List<Cab> candidateCabs,
      @NonNull final Location fromPoint,
      @NonNull final Location toPoint) {
    if (candidateCabs.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(candidateCabs.get(0));
  }
}
