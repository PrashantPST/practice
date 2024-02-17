package design.lld.knearest.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class ElasticsearchService {

  private final RestHighLevelClient client;

  public ElasticsearchService(RestHighLevelClient client) {
    this.client = client;
  }

  public List<Place> findNearestPlaces(double lat, double lon, int k, String placeType)
      throws IOException {
    GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("location")
        .point(lat, lon)
        .distance("10km"); // You can adjust the distance
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
        .query(geoDistanceQueryBuilder)
        .sort(new GeoDistanceSortBuilder("location", lat, lon).order(SortOrder.ASC))
        .size(k);
    SearchRequest searchRequest = new SearchRequest("places").source(searchSourceBuilder);
    SearchResponse response = client.search(searchRequest, null);
    return getPlacesFromResponse(response);
  }

  private List<Place> getPlacesFromResponse(SearchResponse response) {
    List<Place> places = new ArrayList<>();
    for (SearchHit hit : response.getHits().getHits()) {
      // Map the search hit to a Place object
      // This mapping depends on how you've structured your data in Elasticsearch
    }
    return places;
  }
}

