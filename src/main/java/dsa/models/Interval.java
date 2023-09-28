package dsa.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Interval {
    private int start;
    private int end;
}
