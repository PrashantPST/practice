package design.pattern.structural.adaptor.client;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JsonObject {
    private final String requestId;
    private final String metadata;
}
