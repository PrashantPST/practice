package design.lld.cabbooking.models;

import design.lld.cabbooking.enums.Rating;
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
