package design.lld.knearest.redis;


import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

public class RedisGeoExample {
    public static void main(String[] args) {
        // Configure and create a JedisPool
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost");

        // Common categories for all locations
        String[] categories = {"coffee_shop", "restaurant", "book_store"};


        // Use try-with-resources to ensure the Jedis instance is returned to the pool
        try (Jedis jedis = jedisPool.getResource()) {

            // Step 1: Adding Locations with GEOADD M locations
            // O(log(M)) for each item added, where M is the number of elements in the sorted set
            jedis.geoadd("locations", -74.0060, 40.7143, "location1");
            jedis.geoadd("locations", -122.4194, 37.7749, "location2");
            jedis.geoadd("locations", -0.1278, 51.5074, "location3");
            jedis.geoadd("locations", 2.3522, 48.8566, "location4");
            jedis.geoadd("locations", 151.2093, -33.8688, "location5");
            jedis.geoadd("locations", -118.2437, 34.0522, "location6");
            jedis.geoadd("locations", 139.6917, 35.6895, "location7");
            jedis.geoadd("locations", 18.0686, 59.3293, "location8");
            jedis.geoadd("locations", 12.4964, 41.9028, "location9");
            jedis.geoadd("locations", 77.5946, 12.9716, "location10");

            // Step 2: Associating Categories with Locations
            for (int i = 1; i <= 10; i++) {
                jedis.sadd("location" + i + "_", categories);
            }

            // Example: Query for locations within 100 km of a given point
            /*
                The time complexity for georadius command is indeed approximately O(N + log(M))
                O(log(M)): This is the complexity of finding the initial set of candidates within the radius.
                The sorted set structure of Redis allows it to perform this initial range query efficiently in
                logarithmic time relative to the total number of indexed elements.
                O(N): After the initial range query, Redis needs to iterate over these candidate elements
                to confirm whether they indeed fall within the specified radius. This operation is linear
                with respect to the number of elements found within the bounding box.
             */
            List<GeoRadiusResponse> responses = jedis.georadius("locations", -74.0060, 40.7143,
                    4000, GeoUnit.KM);

            for (GeoRadiusResponse response : responses) {
                String locationKey = response.getMemberByString();
                System.out.println("Location: " + locationKey);

                // Step 3: Retrieving Categories for Each Location
                Set<String> associatedCategories = jedis.smembers(locationKey + "_");
                System.out.println("Categories for " + locationKey + ": " + associatedCategories);
            }

        } // Jedis instance will be returned to the pool here
        // Close the JedisPool at the end of your application's lifecycle
        jedisPool.close();
    }
}




