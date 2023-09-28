package design.pattern.structural.adaptor.vendor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class XmlObject {
    private String requestId;
    private String metadata;
}
