package design.lld.cabbooking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rider extends PersonMeta {

    private String id;
    private String name;
}
