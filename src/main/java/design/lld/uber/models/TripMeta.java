package design.lld.uber.models;

import design.lld.uber.enums.Rating;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TripMeta {

    Location srcLoc;
    Location dstLoc;
    Rating riderRating;
    Rating driverRating;
}
