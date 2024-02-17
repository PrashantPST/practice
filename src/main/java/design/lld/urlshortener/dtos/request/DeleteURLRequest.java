package design.lld.urlshortener.dtos.request;

import lombok.Data;

@Data
public class DeleteURLRequest {
    private String apiDevKey;
    private String urlKey;
}
